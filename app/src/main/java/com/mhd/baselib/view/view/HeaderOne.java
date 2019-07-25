package com.mhd.baselib.view.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mhd.baselib.R;
import com.mhd.baselib.databinding.ViewHeaderOneBinding;
import com.mhd.recyclerviewlib.stateView.BaseView;

import java.util.HashMap;

/**
 * @author zhangming
 * @Date 2019/7/24 19:27
 * @Description: 自定义header
 */
public class HeaderOne extends BaseView<String, ViewHeaderOneBinding> {


    public HeaderOne(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.view_header_one;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        mBinding.tvStr.setText("我是头布局1");
    }

    @Override
    protected void initViewConfigure(HashMap<String, Class> viewConfigure) {

    }

    @Override
    protected void initListener(View view, boolean isUpdate) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"我是头布局1",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }
}
