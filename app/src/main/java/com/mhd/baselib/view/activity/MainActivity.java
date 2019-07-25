package com.mhd.baselib.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mhd.baselib.R;
import com.mhd.baselib.adapter.StrsAdapter;
import com.mhd.baselib.view.view.FooterOne;
import com.mhd.baselib.view.view.HeaderOne;
import com.mhd.baselib.view.view.HeaderTwo;
import com.mhd.recyclerviewlib.SimpleRecycleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SimpleRecycleView rvList;
    private StrsAdapter mStrsAdapter;
    private List<String> mDtos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //        try {
        //            Thread.sleep(6000);
        //        } catch (InterruptedException e) {
        //            e.printStackTrace();
        //        }


        initView();
        initData();
        initAdapter();
        rvList.postDelayed(new Runnable() {
            @Override
            public void run() {
                i = count % 9;
                int j = count / 9;
                if (j % 2 == 0) {
                    if (i < 5) {
                        rvList.removeHeaderView(0);
                    } else {
                        rvList.removeFooterView(0);
                    }
                } else {
                    if (i < 5) {
//                        rvList.addHeaderView(HeaderTwo.class);
                        if (i % 3 == 0) {
                            rvList.addHeaderView(HeaderOne.class);
                        } else if (i % 3 == 1) {
                            rvList.addHeaderView(HeaderTwo.class);
                        } else {
                            rvList.addHeaderView(R.layout.view_header_one);
                        }
                    } else {
                        rvList.addFooterView(FooterOne.class);
                    }
                }

                count++;
                //                rvList.getAdapter().notifyDataSetChanged();
                rvList.requestLayout();
                rvList.postDelayed(this, 1000);
            }
        }, 2000);
    }

    int i = 0;
    int count = 0;

    private void initView() {
        rvList = findViewById(R.id.rv_list);
    }

    private void initData() {
        mDtos.add("测试1");
        mDtos.add("测试2");
        mDtos.add("测试3");
        mDtos.add("测试4");
        mDtos.add("测试5");
        mDtos.add("测试6");
        mDtos.add("测试7");
        mDtos.add("测试8");
        mDtos.add("测试9");
        mDtos.add("测试10");
        mDtos.add("测试11");
        mDtos.add("测试12");
        mDtos.add("测试13");
        mDtos.add("测试14");
    }

    private void initAdapter() {
        mStrsAdapter = new StrsAdapter(R.layout.item_str);
        mStrsAdapter.addList(mDtos);
        rvList.startUsingHeaderOrFooter()
                .addHeaderView(HeaderOne.class)
                .addHeaderView(R.layout.view_header_one)
                .addHeaderView(R.layout.view_header_one)
                .addFooterView(FooterOne.class)
                .addFooterView(R.layout.view_header_one)
                .addHeaderView(HeaderTwo.class)
                .addFooterView(R.layout.view_header_one)
                .addHeaderView(HeaderTwo.class)
                .addFooterView(FooterOne.class)
                .setAdapter(mStrsAdapter);
    }
}
