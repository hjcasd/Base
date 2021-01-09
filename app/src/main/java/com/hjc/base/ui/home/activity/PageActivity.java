package com.hjc.base.ui.home.activity;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityPageBinding;
import com.hjc.base.ui.home.adapter.ConcertAdapter;
import com.hjc.base.viewmodel.PageViewModel;
import com.hjc.baselib.activity.BaseActivity;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: Paging
 */
@Route(path = RoutePath.URL_PAGING)
public class PageActivity extends BaseActivity<ActivityPageBinding, PageViewModel> {

    private ConcertAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_page;
    }

    @Override
    protected PageViewModel getViewModel() {
        return new ViewModelProvider(this).get(PageViewModel.class);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mAdapter = new ConcertAdapter();
        mBindingView.rvConcert.setAdapter(mAdapter);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBindingView.rvConcert.setLayoutManager(manager);
    }

    @Override
    protected void observeLiveData() {
        mViewModel.getConvertList().observe(this, concertBeans -> mAdapter.submitList(concertBeans));
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    protected void onSingleClick(View v) {

    }

}
