package com.hjc.library_base.activity;

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
import com.hjc.library_base.utils.ClickUtils;
import com.hjc.library_base.viewmodel.BaseViewModel;

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: (含有Fragment)Activity 基类
 */
public abstract class BaseFragmentActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements View.OnClickListener {

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
    public abstract int getLayoutId();

    private void initViewModel() {
        ARouter.getInstance().inject(this);
        if (mViewModel == null) {
            mViewModel = createViewModel();
        }
    }

    /**
     * 获取viewModel
     */
    public abstract VM createViewModel();

    /**
     * 初始化View
     */
    public void initView() {
        if (getImmersionBar() != null) {
            getImmersionBar().init();
        }
    }

    /**
     * 初始化沉浸式
     */
    public ImmersionBar getImmersionBar() {
        return null;
    }

    /**
     * 初始化数据
     */
    public abstract void initData(@Nullable Bundle savedInstanceState);

    /**
     * 监听LiveData
     */
    public void observeLiveData() {

    }

    /**
     * 设置监听器
     */
    public abstract void addListeners();

    /**
     * 设置点击事件
     */
    public abstract void onSingleClick(View v);

    /**
     * 布局中Fragment的容器ID
     */
    public abstract int getFragmentContentId();

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
    public void showFragment(Fragment fragment) {
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
