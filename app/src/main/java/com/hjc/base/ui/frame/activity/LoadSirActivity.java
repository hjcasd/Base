package com.hjc.base.ui.frame.activity;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityLoadSirBinding;
import com.hjc.base.ui.home.adapter.ArticleAdapter;
import com.hjc.base.viewmodel.LoadSirViewModel;
import com.hjc.baselib.activity.BaseActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: LoadSir + List
 */
@Route(path = RoutePath.URL_LOAD_SIR)
public class LoadSirActivity extends BaseActivity<ActivityLoadSirBinding, LoadSirViewModel> {

    private ArticleAdapter mAdapter;

    //页码
    private int mPage = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_load_sir;
    }

    @Override
    protected LoadSirViewModel getViewModel() {
        return new ViewModelProvider(this).get(LoadSirViewModel.class);
    }

    @Override
    protected void initView() {
        super.initView();

        setLoadSir(mBindingView.smartRefreshLayout);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mBindingView.rvList.setLayoutManager(manager);

        mAdapter = new ArticleAdapter();
        mBindingView.rvList.setAdapter(mAdapter);

        mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mViewModel.loadList(0, true);
    }

    @Override
    protected void observeLiveData() {
        mViewModel.getListLiveData().observe(this, data -> {
            if (mPage == 0) {
                mAdapter.setNewInstance(data);
            } else {
                mAdapter.addData(data);
            }
        });

        mViewModel.getRefreshData().observe(this, result -> {
            if (result) {
                mBindingView.smartRefreshLayout.finishRefresh();
                mBindingView.smartRefreshLayout.finishLoadMore();
            }
        });
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());

        mBindingView.smartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPage++;
                mViewModel.loadList(mPage, false);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPage = 0;
                mViewModel.loadList(mPage, false);
            }
        });
    }

    @Override
    protected void onSingleClick(View v) {

    }

    @Override
    protected void onRetryBtnClick(View v) {
        mPage = 0;
        mViewModel.loadList(mPage, true);
    }
}
