package com.mhd.baseui.shade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mhd.baseui.R;
import com.mhd.baseui.databinding.CommonNetErrorBinding;
import com.mhd.baseui.stateView.BaseView;

import java.util.HashMap;

/**
 * 网络错误
 */
public class NetError extends BaseView<Object, CommonNetErrorBinding> {

    public NetError(Context context, Object data, ViewGroup parent) {
        super(context, data, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.common_net_error;
    }

    @Override
    protected void initView(View view, boolean isUpdate) {
        if (!isUpdate) {
            ((TextView) view.findViewById(R.id.tv_response)).setText("请求失败 稍后重试");
        }
    }

    @Override
    protected void initListener(View view, boolean isUpdate) {
        if (!isUpdate) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if (data instanceof ReplaceInterface) {
//                        ((ReplaceInterface) data).replace();
//                    }
                }
            });
        }
    }

    @Override
    protected void initViewConfigure(HashMap<String, Class> viewConfigure) {

    }

    @Override
    protected ConbinationBuilder combinationViewBuilder() {
        return null;
    }
}
