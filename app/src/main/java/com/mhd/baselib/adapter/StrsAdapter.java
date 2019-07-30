package com.mhd.baselib.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mhd.baselib.R;
import com.mhd.baselib.view.activity.SearchDemo;
import com.mhd.recyclerviewlib.adapter.BaseRecyclerAdapter;

import java.util.HashMap;

/**
 * @author zhangming
 * @Date 2019/3/27 11:07
 * @Description: 车辆数据的adapter
 */
public class StrsAdapter extends BaseRecyclerAdapter<String, StrsAdapter.ViewHolder> {

    private HashMap<String, Class> configViewMap = new HashMap<>();
    private Context mContext;

    public StrsAdapter(int layout) {
        super(null, layout);
        initConfigViewMap();
    }

    private void initConfigViewMap() {
        //        configViewMap.put(DrivingReportDto.DATA, CarDataData.class);

    }

    @Override
    protected ViewHolder createMHDViewHolder(Context mContext, View itemView, int viewType) {
        return new ViewHolder(itemView, String.valueOf(viewType));
    }

    @Override
    protected void bindDate(ViewHolder holder, String str, int position) {
        holder.tvStr.setText(str + "");
    }

    @Override
    protected void itemClick(Context context, String s, int position) {
        Log.e("TTTTTTTTTTTTTTT",position+"|||");
        Toast.makeText(mContext, s, Toast.LENGTH_SHORT).show();
        if ("搜索".equals(s)){
            context.startActivity(new Intent().setClass(context, SearchDemo.class));
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvStr;

        public ViewHolder(View itemView, String viewType) {
            super(itemView);
            initView(itemView, viewType);
        }

        private void initView(View itemView, String viewType) {
            tvStr = itemView.findViewById(R.id.tv_str);
            mContext = itemView.getContext();
        }
    }
}
