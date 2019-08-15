package com.mhd.baselib.view.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mhd.baselib.R;
import com.mhd.baselib.databinding.ViewCustomBinding;

import java.util.HashMap;

import searchcut.airr.searchview.model.SearchModelDto;
import searchcut.airr.stateview.BaseView;


/**
 * @author wangfei
 * @date 2019/7/30.
 */
public class CustomView extends BaseView<SearchModelDto, ViewCustomBinding> {
    public CustomView(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_custom;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {

    }

    @Override
    protected void initListener(View view, boolean isUpdate) {
        mBinding.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchAciton("点击成功");
            }
        });
    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }

    @Override
    protected void initViewConfigure(HashMap viewConfigure) {

    }
}
