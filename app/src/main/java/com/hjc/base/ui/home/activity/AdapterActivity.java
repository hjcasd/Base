package com.hjc.base.ui.home.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityAdapterBinding;
import com.hjc.base.viewmodel.AdapterViewModel;
import com.hjc.baselib.activity.BaseMvmActivity;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: BindingAdapter
 */
@Route(path = RoutePath.URL_BINDING_ADAPTER)
public class AdapterActivity extends BaseMvmActivity<ActivityAdapterBinding, AdapterViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_adapter;
    }

    @Override
    protected AdapterViewModel getViewModel() {
        return new ViewModelProvider(this).get(AdapterViewModel.class);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mBindingView.setAdapterViewModel(mViewModel);
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
            case R.id.btn_pic1:
                mViewModel.loadImage();
                break;

            case R.id.btn_pic2:
                mViewModel.loadRoundImage();
                break;

            case R.id.btn_pic3:
                mViewModel.loadCircleImage();
                break;
        }
    }

}
