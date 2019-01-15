package com.hjc.base.base.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseFragment;
import com.hjc.base.utils.helper.ActivityManager;
import com.hjc.base.utils.FastClickUtils;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:47
 * @Description: (含有Fragment)Activity基类
 */
public abstract class BaseFragmentActivity extends RxFragmentActivity implements View.OnClickListener {
    private Unbinder mBinder;
    private Fragment mCurrentFragment = new Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        mBinder = ButterKnife.bind(this);

        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        initView();
        initData(savedInstanceState);
        addListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.addActivity(this);
    }

    /**
     * 是否使用沉浸式
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        //使用该属性,必须指定状态栏颜色
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.colorPrimary)
                .init();
    }

    /**
     * 获取布局的ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);

    /**
     * 设置监听器
     */
    public abstract void addListeners();

    /**
     * 布局中Fragment的容器ID
     */
    protected abstract int getFragmentContentId();

    /**
     * 设置点击事件
     */
    public abstract void onSingleClick(View v);

    @Override
    public void onClick(View v) {
        //避免快速点击
        if (FastClickUtils.isFastClick()){
            return;
        }
        onSingleClick(v);
    }


    /**
     * 显示fragment
     */
    protected void showFragment(BaseFragment fragment) {
        if (mCurrentFragment != fragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.hide(mCurrentFragment);

            mCurrentFragment = fragment;
            if (!fragment.isAdded()) {
                ft.add(getFragmentContentId(), fragment).show(fragment).commit();
            } else {
                ft.show(fragment).commit();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isImmersionBarEnabled()) {
            ImmersionBar.with(this).destroy();
        }
        if (mBinder != null){
            mBinder.unbind();
        }
    }
}
