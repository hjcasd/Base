package com.hjc.base.ui.home;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityLiveBinding;
import com.hjc.base.viewmodel.LiveViewModel;
import com.hjc.baselib.activity.BaseMvmActivity;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: LiveData + ViewModel
 */
@Route(path = RoutePath.URL_LIVE_DATA)
public class LiveActivity extends BaseMvmActivity<ActivityLiveBinding, LiveViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live;
    }

    @Override
    protected LiveViewModel getViewModel() {
        return new ViewModelProvider(this).get(LiveViewModel.class);
    }

    @Override
    protected int getBindingVariable() {
        return 0;
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

    @Override
    protected void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btnShow:
                mViewModel.showData();
                break;

            case R.id.btnChange:
                mViewModel.changeData();
                break;

            case R.id.btnHide:
                mViewModel.hideData();
                break;
        }
    }

}
