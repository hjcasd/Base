package com.hjc.base.ui.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.databinding.FragmentTab2Binding;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.fragment.BaseFragment;


/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab2
 */
public class Tab2Fragment extends BaseFragment<FragmentTab2Binding, CommonViewModel> {

    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab2;
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

    }

}
