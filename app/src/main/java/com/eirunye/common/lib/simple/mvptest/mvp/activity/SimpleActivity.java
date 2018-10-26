package com.eirunye.common.lib.simple.mvptest.mvp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.eirunye.common.lib.R;
import com.eirunye.common.lib.base.BaseActivity;
import com.eirunye.common.lib.simple.adapter.SimpleTestAdapter;
import com.eirunye.common.lib.simple.mvptest.bean.SimpleBean;
import com.eirunye.common.lib.simple.mvptest.mvp.presenter.SimplePresenter;
import com.eirunye.common.lib.simple.mvptest.mvp.view.SimpleView;

import java.util.ArrayList;
import java.util.List;

public class SimpleActivity extends BaseActivity<SimpleView.presenter> implements SimpleView.view {

    private List<SimpleBean.StoriesBean> list = new ArrayList<>();


    private TextView textView;

    private RecyclerView recycleView;

    private SimpleTestAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        init();
        presenter.getData();
        presenter.getHello();


        ArrayMap<String, String> arrayMap = new ArrayMap<>();
    }

    private void init() {
        recycleView = findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycleView.setLayoutManager(linearLayoutManager);
        simpleAdapter = new SimpleTestAdapter(list);
        recycleView.setAdapter(simpleAdapter);

    }

    @Override
    public SimpleView.presenter initPresenter() {
        return new SimplePresenter(this);
    }

    @Override
    public void setData(List<SimpleBean.StoriesBean> data) {
        list.addAll(data);
        simpleAdapter.notifyDataSetChanged();
    }

    @Override
    public void setHello(String data) {
        Log.e("TAG", "数据" + data);
    }

    @Override
    public void showLoad(String s) {
//        super.showLoad(s);
        Log.e("TAG", "数据" + s);
    }
}
