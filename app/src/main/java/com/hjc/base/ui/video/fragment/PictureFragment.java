package com.hjc.base.ui.video.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.databinding.FragmentPitureBinding;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.fragment.BaseFragment;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab1
 */
public class PictureFragment extends BaseFragment<FragmentPitureBinding, CommonViewModel> {

    public static PictureFragment newInstance() {
        return new PictureFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_piture;
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
