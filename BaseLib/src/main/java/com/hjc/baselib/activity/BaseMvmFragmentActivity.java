package com.hjc.baselib.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.baselib.R;
import com.hjc.baselib.base.BaseActionEvent;
import com.hjc.baselib.base.IBaseView;
import com.hjc.baselib.base.IViewModelAction;
import com.hjc.baselib.dialog.LoadingDialog;
import com.hjc.baselib.fragment.BaseMvmFragment;
import com.hjc.baselib.loadsir.EmptyCallback;
import com.hjc.baselib.loadsir.ErrorCallback;
import com.hjc.baselib.loadsir.LoadingCallback;
import com.hjc.baselib.utils.ClickUtils;
import com.hjc.baselib.utils.helper.ActivityManager;
import com.hjc.baselib.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;


/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: (含有Fragment)Activity 基类
 */
public abstract class BaseMvmFragmentActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends FragmentActivity implements IBaseView, View.OnClickListener {

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    protected LoadService mLoadService;

    private LoadingDialog mLoadingDialog;

    private Fragment mCurrentFragment = new Fragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingView = DataBindingUtil.setContentView(this, getLayoutId());
        mBindingView.setLifecycleOwner(this);

        initViewModel();
        ActivityManager.addActivity(this);

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

        if (mViewModel != null) {
            IViewModelAction viewModelAction = mViewModel;
            viewModelAction.getActionLiveData().observe(this, baseActionEvent -> {
                if (baseActionEvent != null) {
                    switch (baseActionEvent.getAction()) {
                        case BaseActionEvent.START_LOADING_DIALOG: {
                            startLoading();
                            break;
                        }

                        case BaseActionEvent.DISMISS_LOADING_DIALOG: {
                            dismissLoading();
                            break;
                        }

                        case BaseActionEvent.SHOW_LOADING: {
                            showLoading();
                            break;
                        }

                        case BaseActionEvent.SHOW_CONTENT: {
                            showContent();
                            break;
                        }

                        case BaseActionEvent.SHOW_EMPTY: {
                            showEmpty();
                            break;
                        }

                        case BaseActionEvent.SHOW_ERROR: {
                            showError();
                            break;
                        }
                    }
                }
            });
        }
    }

    private void startLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.newInstance();
        }
        mLoadingDialog.showDialog(getSupportFragmentManager());
    }

    private void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.getDialog() != null && mLoadingDialog.getDialog().isShowing()) {
            mLoadingDialog.dismiss();
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
     * 注册LoadSir
     *
     * @param view 状态视图
     */
    protected void setLoadSir(View view) {
        if (mLoadService == null) {
            mLoadService = LoadSir.getDefault().register(view, (Callback.OnReloadListener) this::onRetryBtnClick);
        }
    }

    /**
     * 失败重试,重新加载事件
     */
    protected void onRetryBtnClick(View v) {

    }


    @Override
    public void showContent() {
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }

    @Override
    public void showLoading() {
        if (mLoadService != null) {
            mLoadService.showCallback(LoadingCallback.class);
        }
    }

    @Override
    public void showEmpty() {
        if (mLoadService != null) {
            mLoadService.showCallback(EmptyCallback.class);
        }
    }

    @Override
    public void showError() {
        if (mLoadService != null) {
            mLoadService.showCallback(ErrorCallback.class);
        }
    }

    /**
     * 显示fragment
     */
    protected void showFragment(BaseMvmFragment fragment) {
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
