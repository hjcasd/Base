package com.hjc.base.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.FragmentTab3Binding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.fragment.BaseFragment;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab1
 */
public class Tab3Fragment extends BaseFragment<FragmentTab3Binding, CommonViewModel> {

    public static Tab3Fragment newInstance() {
        return new Tab3Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab3;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                RouteManager.jump(RoutePath.URL_TOUCH);
                break;

            case R.id.btn2:
                RouteManager.jump(RoutePath.URL_VIEW);
                break;

            case R.id.btn3:
               RouteManager.jump(RoutePath.URL_THEME);
                break;

            case R.id.btn4:
                RouteManager.jump(RoutePath.URL_MOTION);
                break;

            default:
                break;
        }
    }

}
