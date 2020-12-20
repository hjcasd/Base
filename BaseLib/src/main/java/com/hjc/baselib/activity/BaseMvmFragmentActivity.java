package com.hjc.baselib.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.baselib.R;
import com.hjc.baselib.utils.ClickUtils;
import com.hjc.baselib.viewmodel.BaseViewModel;


/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: (含有Fragment)Activity 基类
 */
public abstract class BaseMvmFragmentActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements  View.OnClickListener {

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    private Fragment mCurrentFragment = new Fragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingView = DataBindingUtil.setContentView(this, getLayoutId());
        mBindingView.setLifecycleOwner(this);

        initViewModel();
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        initView();
        initData(savedInstanceState);
        observeLiveData();
        addListeners();
    }

    private void initViewModel() {
        if (mViewModel == null) {
            mViewModel = getViewModel();
        }
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
                .statusBarColor(R.color.colorPrimary)
                .fitsSystemWindows(true)
                .init();
    }

    /**
     * 获取布局的ID
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 获取viewModel
     */
    protected abstract VM getViewModel();

    /**
     * 初始化View
     */
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    protected abstract void initData(@Nullable Bundle savedInstanceState);

    /**
     * 监听LiveData
     */
    protected void observeLiveData(){

    }

    /**
     * 设置监听器
     */
    protected abstract void addListeners();

    /**
     * 设置点击事件
     */
    protected abstract void onSingleClick(View v);

    /**
     * 布局中Fragment的容器ID
     */
    protected abstract int getFragmentContentId();

    @Override
    public void onClick(View v) {
        //避免快速点击
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!");
            return;
        }
        onSingleClick(v);
    }

    /**
     * 显示fragment
     */
    protected void showFragment(Fragment fragment) {
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

}
