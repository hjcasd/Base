package com.hjc.library_base.fragment;

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

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.library_base.base.BaseActionEvent;
import com.hjc.library_base.base.ILoadingView;
import com.hjc.library_base.base.IStatusView;
import com.hjc.library_base.base.IViewModelAction;
import com.hjc.library_base.loadsir.DefaultLoadingViewImpl;
import com.hjc.library_base.loadsir.DefaultStatusViewImpl;
import com.hjc.library_base.utils.ClickUtils;
import com.hjc.library_base.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:58
 * @Description: Fragment基类
 */
public abstract class BaseFragment<VDB extends ViewDataBinding, VM extends BaseViewModel> extends Fragment implements View.OnClickListener {

    // Fragment对应的Activity(避免使用getActivity()导致空指针异常)
    protected Context mContext;

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    // IStateView
    private IStatusView mStatusView;

    // ILoadingView
    private ILoadingView mLoadingView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBindingView = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mBindingView.setLifecycleOwner(this);
        return mBindingView.getRoot();
    }

    /**
     * 获取布局的ID
     */
    @LayoutRes
    public abstract int getLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initView();
        initData(savedInstanceState);
        observeLiveData();
        addListeners();
    }

    private void initViewModel() {
        ARouter.getInstance().inject(this);
        if (mViewModel == null) {
            mViewModel = createViewModel();
        }

        if (mViewModel != null) {
            IViewModelAction viewModelAction = mViewModel;
            viewModelAction.getActionLiveData().observe(getViewLifecycleOwner(), baseActionEvent -> {
                if (baseActionEvent != null) {
                    switch (baseActionEvent.getAction()) {
                        case BaseActionEvent.SHOW_LOADING_DIALOG:
                            mLoadingView.showLoading();
                            break;

                        case BaseActionEvent.DISMISS_LOADING_DIALOG:
                            mLoadingView.dismissLoading();
                            break;

                        case BaseActionEvent.SHOW_PROGRESS:
                            mStatusView.showProgress();
                            break;

                        case BaseActionEvent.SHOW_CONTENT:
                            mStatusView.showContent();
                            break;

                        case BaseActionEvent.SHOW_EMPTY:
                            mStatusView.showEmpty(baseActionEvent.getMessage());
                            break;

                        case BaseActionEvent.SHOW_ERROR:
                            mStatusView.showError(baseActionEvent.getMessage());
                            break;

                        case BaseActionEvent.SHOW_TIMEOUT:
                            mStatusView.showTimeout();
                            break;

                        default:
                            break;
                    }
                }
            });
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
        mLoadingView = createLoadingView();
        mStatusView = createStatusView();
    }

    /**
     * 初始化沉浸式
     */
    public ImmersionBar getImmersionBar() {
        return null;
    }

    /**
     * 初始化Loading
     */
    public ILoadingView createLoadingView() {
        return new DefaultLoadingViewImpl(mContext);
    }

    /**
     * 初始化StatusView
     */
    public IStatusView createStatusView() {
        return new DefaultStatusViewImpl();
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
     * @param view     绑定的View
     * @param listener 失败重试,重新加载事件
     */
    public void initLoadSir(View view, Callback.OnReloadListener listener) {
        mStatusView.setLoadSir(view, listener);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && getImmersionBar() != null) {
            getImmersionBar().init();
        }
    }

}
