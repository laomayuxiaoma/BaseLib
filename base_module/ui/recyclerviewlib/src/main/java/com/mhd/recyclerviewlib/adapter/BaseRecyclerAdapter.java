package com.mhd.recyclerviewlib.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.mhd.recyclerviewlib.util.HeaderCount;
import com.mhd.recyclerviewlib.util.OnSingleClickListener;
import com.mhd.recyclerviewlib.util.SingleClickUtil;

import java.util.List;

/**
 * Created by 13660 on 2018/3/23.
 */

public abstract class BaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected List<T> list;
    private int itemLayout;
    protected int duration = 2;

    private HeaderCount mHeaderCount;

    public BaseRecyclerAdapter(List<T> list, int itemLayout) {
        this.list = list;
        this.itemLayout = itemLayout;
    }

    @Override
    public VH onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        final VH recyclerViewHolder;
        VH holder = createMHDViewHolder(parent.getContext(), view, viewType);
        if (holder == null) {
            recyclerViewHolder = (VH) new BaseRecyclerViewHolder(parent.getContext(), view);
        } else {
            recyclerViewHolder = holder;
        }

        SingleClickUtil.setOnSingleClickListener(view, duration, new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {
                int position = recyclerViewHolder.getAdapterPosition() - getHeaderCount();
                if (position < 0) {
                    return;
                }
                if (position < list.size()) {
                    itemClick(parent.getContext(), list.get(position), position);
                } else {
                    itemClick(parent.getContext(), null, position);
                }
            }
        });

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(final VH holder, final int position) {
        if (position < list.size()) {
            bindDate(holder, list.get(position), position);
        } else {
            bindDate(holder, null, position);
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    protected abstract VH createMHDViewHolder(Context mContext, View itemView, int viewType);

    protected abstract void bindDate(VH holder, T t, int position);

    protected abstract void itemClick(Context context, T t, int position);

    public void addList(List<T> addList) {
        this.list = addList;
        notifyDataSetChanged();
    }

    protected List<T> getList() {
        return list;
    }

    private int getHeaderCount() {
        if (mHeaderCount == null) {
            return 0;
        }
        return mHeaderCount.getHeaderCount();
    }

    public void setmHeaderCount(HeaderCount mHeaderCount) {
        this.mHeaderCount = mHeaderCount;
    }
}
