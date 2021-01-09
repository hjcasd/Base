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

import com.alibaba.android.arouter.launcher.ARouter;
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
public abstract class BaseFragmentActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements  View.OnClickListener {

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
        initView();
        initData(savedInstanceState);
        observeLiveData();
        addListeners();
    }

    /**
     * 获取布局的ID
     */
    @LayoutRes
    protected abstract int getLayoutId();

    private void initViewModel() {
        if (mViewModel == null) {
            mViewModel = getViewModel();
        }
    }

    /**
     * 获取viewModel
     */
    protected abstract VM getViewModel();

    /**
     * 初始化View
     */
    protected void initView() {
        ARouter.getInstance().inject(this);
        if (getImmersionBar() != null) {
            getImmersionBar().init();
        }
    }

    /**
     * 初始化沉浸式
     */
    protected ImmersionBar getImmersionBar() {
        return ImmersionBar.with(this)
                .statusBarColor(R.color.base_color)
                .fitsSystemWindows(true);
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

    /**
     * 布局中Fragment的容器ID
     */
    protected abstract int getFragmentContentId();

}
