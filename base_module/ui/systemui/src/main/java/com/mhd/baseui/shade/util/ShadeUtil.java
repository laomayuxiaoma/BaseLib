package com.mhd.baseui.shade.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

import com.mhd.baseui.R;
import com.mhd.baseui.shade.NetLoading;
import com.mhd.baseui.stateView.StateView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * @author zhangming
 * @Date 2019/8/12 14:07
 * @Description: 用于添加view遮罩的工具类
 */
public class ShadeUtil {

    private StateView mStateView;
    private ArrayList<String> mTags = new ArrayList<>();
    private static HashMap<String, Class> mStateMapConfig = new HashMap<>();
    private HashMap<View, StateView> mStateViewMapConfig = new HashMap<>();//view所对应的上层遮罩view
    private HashMap<View, HashMap<String, Class>> mViewsMapConfig = new HashMap<>();//view所对应的

    public static final String NET_LOADING = "NET_LOADING";

    static {
        mStateMapConfig.put(NET_LOADING, NetLoading.class);
    }

    private ShadeUtil() {

    }

    public static ShadeUtil build() {
        return new ShadeUtil();
    }

    public ShadeUtil init(final Context context) {
        if (context instanceof Activity) {
            mTags.clear();
            Set<String> keys = mStateMapConfig.keySet();
            for (String key : keys) {
                mTags.add(key);
            }
            ((Activity) context).getWindow().getDecorView().post(new Runnable() {
                @Override
                public void run() {
                    initViews((ViewGroup) ((Activity) context).getWindow().getDecorView());
                }
            });
        }
        return this;
    }

    private void initViews(ViewGroup group) {
        if (group == null) {
            return;
        }
        int size = group.getChildCount();
        for (int i = 0; i < size; i++) {
            initTagViews(group.getChildAt(i));
            if (group.getChildAt(i) instanceof ViewGroup) {
                initViews((ViewGroup) group.getChildAt(i));
                continue;
            }
        }
    }

    private void initTagViews(View view) {
        if (view.getTag() == view.getResources().getString(R.string.add_shade) || mTags.contains(view.getTag())) {
            addShadeView(view);
        }
    }

    public ShadeUtil addShadeView(View... views) {
        if (views == null || views.length == 0) {
            return this;
        }
        for (final View view : views) {
            if (view == null) {
                continue;
            }
            view.post(new Runnable() {
                @Override
                public void run() {
                    addView(view);
                }
            });
        }
        return this;
    }

    //view默认（默认为初始化）
    public ShadeUtil changeCustomState(String tag, Class viewClass) {
        return changeState(tag, viewClass, mStateView);
    }

    //默认全部修改
    public ShadeUtil changeState(String tag, Class viewClass) {
        return changeState(tag, viewClass, getAllViews());
    }

    public ShadeUtil changeState(String tag, Class viewClass, View... views) {
        if (views == null || views.length == 0) {
            return this;
        }
        for (View view : views) {
            StateView stateView = mStateViewMapConfig.get(view);
            if (stateView != null && mViewsMapConfig.get(stateView) != null) {
                stateView.setData(tag, mViewsMapConfig.get(stateView));
            }
        }
        return this;
    }

    //view默认（默认为初始化）
    public ShadeUtil changeCustomStateConfig(String tag, Class viewClass) {
        return changeStateConfig(tag, viewClass, mStateView);
    }

    //默认全部修改
    public ShadeUtil changeStateConfig(String tag, Class viewClass) {
        return changeStateConfig(tag, viewClass, getAllViews());
    }

    private View[] getAllViews() {
        Set<View> views = mStateViewMapConfig.keySet();
        View[] targetViews = new View[views.size()];
        int i = 0;
        for (View view : views) {
            targetViews[i++] = view;
        }
        return targetViews;
    }

    public ShadeUtil changeStateConfig(final String tag, final Class viewClass, View... views) {
        if (views == null || views.length == 0) {
            return this;
        }
        for (final View view : views) {
            view.post(new Runnable() {
                @Override
                public void run() {
                    StateView stateView = mStateViewMapConfig.get(view);
                    stateView.setCacheView(false);
                    stateView.setForceNew(true);
                    if (stateView != null && mViewsMapConfig.get(stateView) != null) {
                        mViewsMapConfig.get(stateView).put(tag, viewClass);
                        stateView.setData(NET_LOADING, mViewsMapConfig.get(stateView));
                    }
                }
            });
        }
        return this;
    }

    //添加view
    private void addView(View view) {
        ViewParent parent = view.getParent();
        if (!(parent instanceof ViewGroup)) {
            return;
        }
        int position = getViewPosition((ViewGroup) parent, view);
        if (position == -1) {
            return;
        }
        int id = view.getId();
        view.setId(-1);
        FrameLayout frameLayout = new FrameLayout(view.getContext());
        frameLayout.setId(id);
        frameLayout.setLayoutParams(view.getLayoutParams());
        ((ViewGroup) parent).addView(frameLayout, position);
        ((ViewGroup) parent).removeViewAt(position + 1);
        StateView stateView = initStateView(view);
        view.getLayoutParams().width = view.getMeasuredWidth();
        view.getLayoutParams().height = view.getMeasuredHeight();
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //        Log.e("TTTTTTTTTYY", view.getMeasuredWidth() + "||" + view.getMeasuredHeight());
        frameLayout.addView(view);
        frameLayout.addView(stateView);
    }

    private StateView initStateView(View view) {
        StateView stateView = new StateView(view.getContext());
        stateView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mStateViewMapConfig.put(view, stateView);
        mViewsMapConfig.put(stateView, (HashMap<String, Class>) mStateMapConfig.clone());
        if (view.getTag() != null && mViewsMapConfig.get(stateView).get(view.getTag().toString()) != null) {
            stateView.setData(view.getTag().toString(), mViewsMapConfig.get(stateView));
        } else {
            stateView.setData(NET_LOADING, mViewsMapConfig.get(stateView));
        }
        view.setTag("");
        return stateView;
    }

    private int getViewPosition(ViewGroup group, View view) {
        int size = group.getChildCount();
        for (int i = 0; i < size; i++) {
            if (view == group.getChildAt(i)) {
                return i;
            }
        }
        return -1;
    }

    public ShadeUtil shadeDismiss() {
        return shadeDismiss(getAllViews());
    }

    public ShadeUtil shadeDismiss(View... views) {
        for (View view : views) {
            StateView stateView = mStateViewMapConfig.get(view);
            if (stateView != null) {
                stateView.removeAllViews();
                stateView.setVisibility(View.GONE);
            }
        }
        return this;
    }

    public ShadeUtil setOnClickListener(View view, View.OnClickListener clickListener) {
        if (!(view.getParent() instanceof FrameLayout)) {
            return this;
        }
        ((FrameLayout) view.getParent()).setOnClickListener(clickListener);
        return this;
    }

    public ShadeUtil put(String key, Class view) {
        mStateMapConfig.put(key, view);
        return this;
    }

    public static HashMap<String, Class> getmStateMapConfig() {
        return mStateMapConfig;
    }
}