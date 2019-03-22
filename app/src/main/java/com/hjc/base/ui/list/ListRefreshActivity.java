package com.hjc.base.ui.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.widget.TitleBar;
import com.hjc.base.widget.helper.LinearItemDecoration;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListRefreshActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.smart_refresh_layout)
    SmartRefreshLayout smartRefreshLayout;

    private RefreshAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_list_refresh;
    }

    @Override
    public void initView() {
        MaterialHeader materialHeader = new MaterialHeader(this);
        smartRefreshLayout.setRefreshHeader(materialHeader);
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(new LinearItemDecoration(this, LinearItemDecoration.VERTICAL_LIST, R.drawable.shape_rv_divider));
        mAdapter = new RefreshAdapter(generateData());
        rvList.setAdapter(mAdapter);

        mAdapter.openLoadAnimation();
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewClickListener(new TitleBar.onViewClick() {
            @Override
            public void leftClick(View view) {
                finish();
            }

            @Override
            public void rightClick(View view) {

            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.addData(generateData());
                smartRefreshLayout.finishRefresh(2000);
            }
        });
    }

    private List<String> generateData(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("我是第" + i + "个条目");
        }
        return list;
    }

    @Override
    public void onSingleClick(View v) {

    }
}
