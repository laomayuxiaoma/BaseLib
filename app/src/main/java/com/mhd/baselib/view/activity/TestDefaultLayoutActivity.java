package com.mhd.baselib.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mhd.baselib.R;
import com.mhd.baseui.shade.NetImageLoading;
import com.mhd.baseui.shade.util.ShadeUtil;


public class TestDefaultLayoutActivity extends AppCompatActivity implements Runnable {

    public String NET_IMAGE_LOADING;

    private ShadeUtil mShadeUtil;
    private TextView tvTitle;
    private ImageView imgHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_default_layout);
        tvTitle = findViewById(R.id.tv_title);
        imgHead = findViewById(R.id.img_head);

        initTags();

        tvTitle.postDelayed(this, 3000);
    }

    private void initTags() {
        NET_IMAGE_LOADING = this.getResources().getString(R.string.net_image_loading);
        mShadeUtil = ShadeUtil.build()
                .put(NET_IMAGE_LOADING, NetImageLoading.class)
                .init(this);
        mShadeUtil.changeStateConfig(ShadeUtil.NET_LOADING, NetImageLoading.class, imgHead);
    }

    @Override
    public void run() {
        mShadeUtil.shadeDismiss();
    }
}