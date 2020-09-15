package com.hjc.baselib.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

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
import com.hjc.baselib.utils.ClickUtils;
import com.hjc.baselib.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:58
 * @Description: Fragment基类(用于懒加载)
 */
public abstract class BaseMvmLazyFragment<VDB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements IBaseView, View.OnClickListener {
    /**
     * Fragment对应的Activity(避免使用getActivity()导致空指针异常)
     */
    protected Context mContext;

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    protected LoadService mLoadService;

    private LoadingDialog mLoadingDialog;

    protected ImmersionBar mImmersionBar;

    /**
     * 判断View是否加载完成
     */
    private boolean isViewCreated;

    /**
     * 判断当前Fragment是否可见
     */
    private boolean isFragmentVisible;

    /**
     * 判断是否第一次加载数据
     */
    private boolean isFirstLoad = true;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible = isVisibleToUser;
        lazyLoad();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBindingView = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        isViewCreated = true;
        return mBindingView.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        lazyLoad();
    }

    /**
     * 懒加载
     */
    private void lazyLoad() {
        if (isViewCreated && isFragmentVisible && isFirstLoad) {
            isFirstLoad = false;
            initView();
            initData();
            observeLiveData();
            addListeners();
        }
    }

    private void initViewModel() {
        if (mViewModel == null) {
            mViewModel = getViewModel();
        }

        mBindingView.setLifecycleOwner(this);
        if (getBindingVariable() > 0) {
            mBindingView.setVariable(getBindingVariable(), mViewModel);
        }
        mBindingView.executePendingBindings();

        if (mViewModel != null) {
            IViewModelAction viewModelAction = mViewModel;
            viewModelAction.getActionLiveData().observe(getViewLifecycleOwner(), baseActionEvent -> {
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
        mLoadingDialog.showDialog(getChildFragmentManager());
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
        return false;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        //使用该属性,必须指定状态栏颜色
        mImmersionBar
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
     * 获取参数Variable
     */
    protected abstract int getBindingVariable();

    /**
     * 初始化View
     */
    protected void initView() {

    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.init();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
    }
}
