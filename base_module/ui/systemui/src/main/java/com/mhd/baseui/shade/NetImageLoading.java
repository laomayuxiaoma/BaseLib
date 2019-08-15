package com.mhd.baseui.shade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mhd.baseui.R;
import com.mhd.baseui.databinding.CommonNetLoadingBinding;
import com.mhd.baseui.stateView.BaseView;

import java.util.HashMap;

/**
 * 加载中
 */
public class NetImageLoading extends BaseView<Object, CommonNetLoadingBinding> {


    public NetImageLoading(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.common_net_loading;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (!isUpdate) {
            mBinding.ivLoad.setBackgroundResource(R.drawable.linear_backgroud_grayshape);
//            ImageLoader.loadImageViewDynamicGif(view.getContext(), R.drawable.loading, (ImageView) view.findViewById(R.id.iv_load));
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
