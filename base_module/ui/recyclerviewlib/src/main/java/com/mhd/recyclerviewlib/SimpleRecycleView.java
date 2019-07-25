package com.mhd.recyclerviewlib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.mhd.recyclerviewlib.adapter.HeaderAndFooterAdapter;

/**
 * @author zhangming
 * @Date 2019/7/24 17:36
 * @Description: 简化的RecycleView(简化初始化 ， 链式调用 ， 可以添加头部及尾部布局), 默认为垂直布局
 */
public class SimpleRecycleView extends RecyclerView {

    private boolean isStartUsingHeaderOrFooter;

    private HeaderAndFooterAdapter mHeaderAndFooterAdapter;

    public SimpleRecycleView(@NonNull Context context) {
        super(context);
        init();
    }

    public SimpleRecycleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleRecycleView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new LinearLayoutManager(getContext()));
    }

    /**
     * 添加item之间的间隔
     *
     * @param res         Drawable资源
     * @param orientation 方向如：DividerItemDecoration.VERTICAL
     */
    public SimpleRecycleView addItemDecoration(@NonNull int res, int orientation) {
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), orientation);
        dividerItemDecoration.setDrawable(getResources().getDrawable(res));
        super.addItemDecoration(dividerItemDecoration);

        return this;
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter == null) {
            mHeaderAndFooterAdapter = new HeaderAndFooterAdapter(getContext(), adapter);
        }

        if (mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.setmInnerAdapter(adapter);
        }

        super.setAdapter(mHeaderAndFooterAdapter == null ? adapter : mHeaderAndFooterAdapter);
    }

    public SimpleRecycleView addHeaderView(int layoutRes) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.addHeaderView(layoutRes);
        }
        return this;
    }

    public SimpleRecycleView addFooterView(int layoutRes) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.addFooterView(layoutRes);
        }
        return this;
    }

    public SimpleRecycleView addHeaderView(Class view) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.addHeaderView(view);
        }
        return this;
    }

    public SimpleRecycleView addFooterView(Class view) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.addFooterView(view);
        }
        return this;
    }

    //起始为0，第一个头部布局为0
    public SimpleRecycleView setHeaderViewShow(Integer position, boolean isShow) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.setHeaderViewShow(position, isShow);
        }
        return this;
    }

    //起始为0，第一个底部布局为0
    public SimpleRecycleView setFooterViewShow(Integer position, boolean isShow) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.setFooterViewShow(position, isShow);
        }
        return this;
    }

    //起始为0，第一个头部布局为0
    public SimpleRecycleView removeHeaderView(Integer position) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.removeHeaderView(position);
        }
        return this;
    }

    //起始为0，第一个底部布局为0
    public SimpleRecycleView removeFooterView(Integer position) {
        if (isStartUsingHeaderOrFooter && mHeaderAndFooterAdapter != null) {
            mHeaderAndFooterAdapter.removeFooterView(position);
        }
        return this;
    }


    public SimpleRecycleView startUsingHeaderOrFooter() {
        isStartUsingHeaderOrFooter = true;
        if (mHeaderAndFooterAdapter == null) {
            mHeaderAndFooterAdapter = new HeaderAndFooterAdapter(getContext(), getAdapter());
        }
        if (getAdapter() != null) {
            super.setAdapter(mHeaderAndFooterAdapter);
        }
        return this;
    }

}
