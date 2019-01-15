package com.hjc.base.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.hjc.base.R;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:58
 * @Description: Fragment基类(带沉浸式)
 */
public abstract class BaseImmersionFragment extends BaseFragment implements View.OnClickListener {
    protected ImmersionBar mImmersionBar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initImmersionBar();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null){
            mImmersionBar.init();
        }
    }

    /**
     * 初始化沉浸式
     */
    private void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //使用该属性,必须指定状态栏颜色
        mImmersionBar.fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
                .init();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }
}
