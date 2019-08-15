package com.mhd.baseui.shade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhd.baseui.R;
import com.mhd.baseui.databinding.CommonNetNoDataBinding;
import com.mhd.baseui.stateView.BaseView;

import java.util.HashMap;

/**
 * 暂无数据
 */
public class NetNoData extends BaseView<Object, CommonNetNoDataBinding> {


    public NetNoData(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.common_net_no_data;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (!isUpdate) {
            ((TextView) view.findViewById(R.id.tv_response)).setText("暂无数据");
        }
    }

    @Override
    protected void initListener(View view, boolean isUpdate) {

    }

    @Override
    protected void initViewConfigure(HashMap<String, Class> viewConfigure) {

    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }
}
