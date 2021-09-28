package com.hjc.base.ui.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.databinding.FragmentTab4Binding;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.fragment.BaseFragment;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab4
 */
public class Tab4Fragment extends BaseFragment<FragmentTab4Binding, CommonViewModel> {

    public static Tab4Fragment newInstance() {
        return new Tab4Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab4;
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
