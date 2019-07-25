package com.mhd.recyclerviewlib.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhd.recyclerviewlib.R;
import com.mhd.recyclerviewlib.stateView.StateView;

import java.util.HashMap;
import java.util.Set;

/**
 * @author zhangming
 * @Date 2019/7/24 17:16
 * @Description: 当recycleview添加顶部或底部布局时使用
 */
public class HeaderAndFooterAdapter extends RecyclerView.Adapter {

    public static final int BASE_ITEM_TYPE_HEADER = 100000;
    public static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private Object headerOrFooterData;
    private HashMap<String, Class> configViewMap = new HashMap<>();

    private SparseArrayCompat<Object> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<Object> mFooterViews = new SparseArrayCompat<>();
    private SparseArrayCompat<Boolean> isShowViews = new SparseArrayCompat<>();
    private SparseArrayCompat<Integer> mHeaderViewsIdCache = new SparseArrayCompat<>();
    private SparseArrayCompat<Integer> mFooterViewsIdCache = new SparseArrayCompat<>();

    private RecyclerView.Adapter mInnerAdapter;
    private Context mContet;

    public HeaderAndFooterAdapter(Context mContet, RecyclerView.Adapter innerAdapter) {
        this.mContet = mContet;
        mInnerAdapter = innerAdapter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header_or_footer, parent, false);
            return new ViewHolder(view, viewType + "", true);
        } else if (mFooterViews.get(viewType) != null) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_header_or_footer, parent, false);
            return new ViewHolder(view, viewType + "");
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {

            if (isShowViews.get(getItemViewType(position), true)) {
                ((ViewHolder) (viewHolder)).mStateView.setVisibility(View.VISIBLE);
            } else {
                ((ViewHolder) (viewHolder)).mStateView.setVisibility(View.GONE);
            }

            if (configViewMap.get(getItemViewType(position) + "") != null) {
                ((ViewHolder) (viewHolder)).mStateView.setData(String.valueOf(getItemViewType(position)), configViewMap, headerOrFooterData);
            }
            return;
        }
        mInnerAdapter.onBindViewHolder(viewHolder, position - getHeadersCount());
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return super.getItemViewType(position - getHeadersCount());
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }

    public HeaderAndFooterAdapter setHeaderOrFooterData(Object headerOrFooterData) {
        this.headerOrFooterData = headerOrFooterData;
        return this;
    }


    /*** 适配网格布局 * @param recyclerView*/
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        if (mInnerAdapter == null) {
            super.onAttachedToRecyclerView(recyclerView);
            return;
        }
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return isHeaderViewPos(position) || isFooterViewPos(position) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
        }
    }


    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        if (mInnerAdapter == null) {
            super.onViewAttachedToWindow(holder);
            return;
        }
        mInnerAdapter.onViewAttachedToWindow(holder);
        int position = holder.getLayoutPosition();
        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
    }


    /*** 获取正常数据的size
     ** @return
     **/
    private int getRealItemCount() {
        return mInnerAdapter.getItemCount();
    }

    /*** 判断是否是Header
     ** @param position
     ** @return*/
    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    /*** 判断是否是Footer
     ** @param position
     ** @return*/
    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    public HeaderAndFooterAdapter addHeaderView(Class view) {
        int id = getCacheId(true);
        configViewMap.put((id == -1 ? (mHeaderViews.size() + BASE_ITEM_TYPE_HEADER) : id) + "", view);
        mHeaderViews.put(id == -1 ? mHeaderViews.size() + BASE_ITEM_TYPE_HEADER : id, view);
        return this;
    }

    public HeaderAndFooterAdapter addFooterView(Class view) {
        int id = getCacheId(false);
        configViewMap.put((id == -1 ? (mFooterViews.size() + BASE_ITEM_TYPE_FOOTER) : id) + "", view);
        mFooterViews.put(id == -1 ? mFooterViews.size() + BASE_ITEM_TYPE_FOOTER : id, view);
        return this;
    }

    public HeaderAndFooterAdapter addHeaderView(Integer layoutRes) {
        int id = getCacheId(true);
        mHeaderViews.put(id == -1 ? mHeaderViews.size() + BASE_ITEM_TYPE_HEADER : id, layoutRes);
        return this;
    }

    public HeaderAndFooterAdapter addFooterView(Integer layoutRes) {
        int id = getCacheId(false);
        mFooterViews.put(id == -1 ? mFooterViews.size() + BASE_ITEM_TYPE_FOOTER : id, layoutRes);
        return this;
    }

    //起始为0，第一个头部布局为0
    public HeaderAndFooterAdapter setHeaderViewShow(Integer position, boolean isShow) {
        isShowViews.put(position + BASE_ITEM_TYPE_HEADER, isShow);
        notifyDataSetChanged();
        return this;
    }

    //起始为0，第一个底部布局为0
    public HeaderAndFooterAdapter setFooterViewShow(Integer position, boolean isShow) {
        isShowViews.put(position + BASE_ITEM_TYPE_FOOTER, isShow);
        notifyDataSetChanged();
        return this;
    }

    //起始为0，第一个头部布局为0
    public HeaderAndFooterAdapter removeHeaderView(Integer position) {
        int key = mHeaderViews.keyAt(position);
        mHeaderViewsIdCache.put(mHeaderViewsIdCache.size(), key);
        removeConfig(key);
        mHeaderViews.remove(key);
        notifyDataSetChanged();
        return this;
    }

    private void removeConfig(int key) {
        String strKey = key + "";
        Set<String> strings = configViewMap.keySet();
        for (String str : strings) {
            if (strKey.equalsIgnoreCase(str)) {
                configViewMap.remove(str);
                return;
            }
        }
    }

    //起始为0，第一个底部布局为0
    public HeaderAndFooterAdapter removeFooterView(Integer position) {
        int key = mFooterViews.keyAt(position);
        mFooterViewsIdCache.put(mHeaderViewsIdCache.size(), key);
        removeConfig(key);
        mFooterViews.remove(key);
        notifyDataSetChanged();
        return this;
    }

    public HeaderAndFooterAdapter setmInnerAdapter(RecyclerView.Adapter mInnerAdapter) {
        this.mInnerAdapter = mInnerAdapter;
        return this;
    }

    private Integer getCacheId(boolean isHeader) {
        if (isHeader && mHeaderViewsIdCache.size() != 0) {
            Integer id = mHeaderViewsIdCache.get(mHeaderViewsIdCache.keyAt(0), BASE_ITEM_TYPE_HEADER);
            mHeaderViewsIdCache.removeAt(0);
            return id;
        } else if (!isHeader && mFooterViewsIdCache.size() != 0) {
            Integer id = mFooterViewsIdCache.get(mFooterViewsIdCache.keyAt(0), BASE_ITEM_TYPE_FOOTER);
            mFooterViewsIdCache.removeAt(0);
            return id;
        }
        return -1;
    }

    private int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private StateView mStateView;
        private boolean isHeader;

        public ViewHolder(View itemView, String viewType) {
            super(itemView);
            initView(itemView, viewType);
        }

        public ViewHolder(View itemView, String viewType, boolean isHeader) {
            super(itemView);
            this.isHeader = isHeader;
            initView(itemView, viewType);
        }

        private void initView(View itemView, String viewType) {
            mStateView = itemView.findViewById(R.id.sv_headerOrFooter);
            mStateView.setCacheView(false)
                    .setForceNew(true);
            if (configViewMap.get(viewType) == null && ((isHeader && mHeaderViews.get(Integer.valueOf(viewType)) instanceof Integer) ||
                    (!isHeader && mFooterViews.get(Integer.valueOf(viewType)) instanceof Integer))) {
                mStateView.removeAllViews();
                mStateView.addView(isHeader ? getItemView((Integer) mHeaderViews.get(Integer.valueOf(viewType))) : getItemView((Integer) mFooterViews.get(Integer.valueOf(viewType))));
            } else if (configViewMap.get(viewType) != null) {
                mStateView.setData(viewType, configViewMap);
            }
        }

        private View getItemView(int itemRes) {
            return LayoutInflater.from(mContet).inflate(itemRes, mStateView, false);
        }
    }

}
