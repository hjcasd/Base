package com.hjc.base.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.FragmentTab1Binding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.fragment.BaseFragment;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab1
 */
public class Tab1Fragment extends BaseFragment<FragmentTab1Binding, CommonViewModel> {

    public static Tab1Fragment newInstance() {
        return new Tab1Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab1;
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
                RouteManager.jump(RoutePath.URL_DATA_BINDING);
                break;

            case R.id.btn2:
                RouteManager.jump(RoutePath.URL_LIVE_DATA);
                break;

            case R.id.btn3:
                RouteManager.jump(RoutePath.URL_BINDING_ADAPTER);
                break;

            case R.id.btn4:
                RouteManager.jump(RoutePath.URL_ROOM);
                break;

            case R.id.btn5:
                RouteManager.jump(RoutePath.URL_PAGING);
                break;

            default:
                break;
        }
    }

}
