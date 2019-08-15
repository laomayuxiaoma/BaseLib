package com.mhd.baselib.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mhd.baselib.R;
import com.mhd.baselib.view.view.CustomView;

import java.util.ArrayList;
import java.util.List;

import searchcut.airr.searchview.icallback.ICallBack;
import searchcut.airr.searchview.model.SearchDataDto;
import searchcut.airr.searchview.view.SearchView;


public class SearchDemo extends AppCompatActivity {

    // 1. 初始化搜索框变量
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 2. 绑定视图
        setContentView(R.layout.activity_search);


        // 3. 绑定组件
        searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                Toast.makeText(SearchDemo.this, "我收到了" + string, Toast.LENGTH_SHORT).show();
            }
        }).setFuzzyData(initData())
                .addOtherView("1", CustomView.class, new SearchDataDto())
                .addOtherView("2", CustomView.class, new SearchDataDto())
                .setCurrentPageShow(false)
        ;

//        ((TextView) findViewById(R.id.text)).setText(checkString("hhhhhhh") + "");


    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        list.add("啦啦啦啦啦啦啦哈哈哈哈哈");
        list.add("你好吗");
        list.add("啦啦哈哈");
        list.add("你");
        list.add("哈哈六六六");
        list.add("我");
        list.add("aaa");
        list.add("abc");
        list.add("abcdfghr");
        list.add("bdbjdbcjs");
        list.add("cdslmcdl");
        list.add("dkjflksflas");
        list.add("efdfdfdlk");
        return list;
    }

//    private boolean checkString(String s) {
//        return s.matches("^([a-zA-Z])(?<=[a-zA-Z0-9]$)[a-zA-Z0-9_]+$");
//    }
}