package com.hjc.baselib.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.baselib.R;
import com.hjc.baselib.base.BaseActionEvent;
import com.hjc.baselib.base.IBaseView;
import com.hjc.baselib.base.IViewModelAction;
import com.hjc.baselib.dialog.LoadingDialog;
import com.hjc.baselib.loadsir.EmptyCallback;
import com.hjc.baselib.loadsir.ErrorCallback;
import com.hjc.baselib.loadsir.LoadingCallback;
import com.hjc.baselib.loadsir.TimeoutCallback;
import com.hjc.baselib.utils.ClickUtils;
import com.hjc.baselib.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;


/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: Activity 基类
 */
public abstract class BaseActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements IBaseView, View.OnClickListener {

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    protected LoadService<?> mLoadService;

    private LoadingDialog mLoadingDialog;


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

        if (mViewModel != null) {
            IViewModelAction viewModelAction = mViewModel;
            viewModelAction.getActionLiveData().observe(this, baseActionEvent -> {
                if (baseActionEvent != null) {
                    switch (baseActionEvent.getAction()) {
                        case BaseActionEvent.SHOW_LOADING_DIALOG:
                            showLoading();
                            break;

                        case BaseActionEvent.DISMISS_LOADING_DIALOG:
                            dismissLoading();
                            break;

                        case BaseActionEvent.SHOW_PROGRESS:
                            showProgress();
                            break;

                        case BaseActionEvent.SHOW_CONTENT:
                            showContent();
                            break;

                        case BaseActionEvent.SHOW_EMPTY:
                            showEmpty();
                            break;

                        case BaseActionEvent.SHOW_ERROR:
                            showError();
                            break;

                        case BaseActionEvent.SHOW_TIMEOUT:
                            showTimeout();
                            break;
                    }
                }
            });
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
    protected void observeLiveData() {

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
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.newInstance();
        }
        mLoadingDialog.showDialog(getSupportFragmentManager());
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null && mLoadingDialog.getDialog() != null && mLoadingDialog.getDialog().isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showContent() {
        if (mLoadService != null) {
            mLoadService.showSuccess();
        }
    }

    @Override
    public void showProgress() {
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

    @Override
    public void showTimeout() {
        if (mLoadService != null) {
            mLoadService.showCallback(TimeoutCallback.class);
        }
    }

}
