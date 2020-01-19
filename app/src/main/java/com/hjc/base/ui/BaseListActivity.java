package com.hjc.base.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.baselib.event.EventManager;
import com.hjc.baselib.event.MessageEvent;
import com.hjc.baselib.mvp.IBaseView;
import com.hjc.baselib.utils.ClickUtils;
import com.hjc.baselib.utils.helper.ActivityManager;
import com.hjc.baselib.widget.StatusView;
import com.hjc.baselib.widget.bar.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:25
 * @Description: 主页
 */
public abstract class BaseListActivity extends RxAppCompatActivity implements View.OnClickListener, IBaseView {
    private Unbinder mBinder;

    private TitleBar titleBar;
    private SmartRefreshLayout smartRefreshLayout;
    private StatusView statusView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        initView();
        initData(savedInstanceState);
        addListeners();
    }

    @Override
    public void setContentView(int layoutResID) {
        View rootView = LayoutInflater.from(this).inflate(R.layout.activity_base_list, null, false);
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null, false);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentView.setLayoutParams(params);

        titleBar = rootView.findViewById(R.id.title_bar);
        smartRefreshLayout = rootView.findViewById(R.id.smart_refresh_layout);
        statusView = rootView.findViewById(R.id.status_view);

        statusView.addView(contentView);
        getWindow().setContentView(rootView);

        initSmartRefreshLayout();
        initTitleBar();
    }

    protected void initSmartRefreshLayout() {
//        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(false);

    }

    protected void initTitleBar() {
        titleBar.setTitle(getPageTitle());
        titleBar.setOnViewLeftClickListener(view -> finish());
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
    public abstract int getLayoutId();

    public abstract String getPageTitle();

    /**
     * 初始化View
     */
    protected void initView() {
        mBinder = ButterKnife.bind(this);

        if (isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }

    /**
     * 初始化数据
     */
    protected void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        ActivityManager.addActivity(this);

        EventManager.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveEvent(MessageEvent event){

    }

    /**
     * 设置监听器
     */
    protected void addListeners() {

    }

    /**
     * 设置点击事件
     */
    protected void onSingleClick(View v) {

    }

    @Override
    public void onClick(View v) {
        //避免快速点击
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!");
            return;
        }
        onSingleClick(v);
    }


    @Override
    public void showLoading() {
        statusView.showLoading();
    }

    @SuppressLint("CheckResult")
    @Override
    public void showContent() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    statusView.showContent();
                    smartRefreshLayout.finishLoadMore();
                    smartRefreshLayout.finishRefresh();

                    if (smartRefreshLayout.getState().isFooter) {
                        smartRefreshLayout.setEnableLoadMore(true);
                    } else {
                        smartRefreshLayout.setEnableLoadMore(false);
                    }
                });

    }

    @Override
    public void showEmpty() {
        statusView.showEmpty();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void showError() {
        statusView.showError();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    public void showNoNetwork() {
        statusView.showNoNetwork();
        smartRefreshLayout.finishRefresh();
        smartRefreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBinder != null) {
            mBinder.unbind();
        }
        EventManager.unregister(this);
    }
}
