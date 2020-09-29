package com.hjc.base.ui.other.activity;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityLoginBinding;
import com.hjc.base.viewmodel.LoginViewModel;
import com.hjc.baselib.activity.BaseMvmActivity;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: LiveData + ViewModel + Retrofit
 */
@Route(path = RoutePath.URL_LOGIN)
public class LoginActivity extends BaseMvmActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginViewModel getViewModel() {
        return new ViewModelProvider(this).get(LoginViewModel.class);
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mBindingView.setLoginViewModel(mViewModel);
    }

    @Override
    protected void observeLiveData() {
        mViewModel.getLoginData().observe(this, loginResp -> {
            LogUtils.e(loginResp.getToken());
            mBindingView.etPhone.clearFocus();
            mBindingView.etCode.clearFocus();
        });
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    protected void onSingleClick(View v) {
        if (v.getId() == R.id.btn_login) {
            mViewModel.login();
        }
    }

}
