package com.hjc.base.ui.home.activity;


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
    protected int getBindingVariable() {
        return 0;
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

    @Override
    protected void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btnPic1:
                mViewModel.loadImage();
                break;

            case R.id.btnPic2:
                mViewModel.loadRoundImage();
                break;

            case R.id.btnPic3:
                mViewModel.loadCircleImage();
                break;
        }
    }

}
