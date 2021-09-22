package com.hjc.library_base.activity;

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
import com.hjc.library_base.base.BaseActionEvent;
import com.hjc.library_base.base.ILoadingView;
import com.hjc.library_base.base.IStatusView;
import com.hjc.library_base.base.IViewModelAction;
import com.hjc.library_base.loadsir.BaseLoadingViewImpl;
import com.hjc.library_base.loadsir.BaseStatusViewImpl;
import com.hjc.library_base.utils.ClickUtils;
import com.hjc.library_base.viewmodel.BaseViewModel;
import com.kingja.loadsir.callback.Callback;


/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: Activity 基类
 */
public abstract class BaseActivity<VDB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements View.OnClickListener {

    // ViewDataBinding
    protected VDB mBindingView;

    // ViewModel
    protected VM mViewModel;

    // IStateView
    private IStatusView mStatusView;

    // ILoadingView
    private ILoadingView mLoadingView;


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
        ARouter.getInstance().inject(this);
        if (mViewModel == null) {
            mViewModel = createViewModel();
        }

        if (mViewModel != null) {
            IViewModelAction viewModelAction = mViewModel;
            viewModelAction.getActionLiveData().observe(this, baseActionEvent -> {
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
        return new BaseLoadingViewImpl(this);
    }

    /**
     * 初始化StatusView
     */
    public IStatusView createStatusView() {
        return new BaseStatusViewImpl();
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

}
