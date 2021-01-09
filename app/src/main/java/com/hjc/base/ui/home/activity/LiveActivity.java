package com.hjc.base.ui.home.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityLiveBinding;
import com.hjc.base.viewmodel.LiveViewModel;
import com.hjc.baselib.activity.BaseActivity;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: LiveData + ViewModel
 */
@Route(path = RoutePath.URL_LIVE_DATA)
public class LiveActivity extends BaseActivity<ActivityLiveBinding, LiveViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    protected LiveViewModel getViewModel() {
        return new ViewModelProvider(this).get(LiveViewModel.class);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mBindingView.setLiveViewModel(mViewModel);
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                mViewModel.showData();
                break;

            case R.id.btn_change:
                mViewModel.changeData();
                break;

            case R.id.btn_hide:
                mViewModel.hideData();
                break;
        }
    }

}
