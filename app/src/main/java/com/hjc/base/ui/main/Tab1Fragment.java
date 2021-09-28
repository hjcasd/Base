package com.hjc.base.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.databinding.FragmentTab1Binding;
import com.hjc.base.router.RouteManager;
import com.hjc.base.router.RoutePath;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.fragment.BaseFragment;

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
    public CommonViewModel createViewModel() {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        if (v.getId() == R.id.btn1){
            RouteManager.jump(RoutePath.URL_VIDEO);
        }
    }

}
