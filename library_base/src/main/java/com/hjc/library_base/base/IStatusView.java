package com.hjc.library_base.base;

import android.view.View;

import com.kingja.loadsir.callback.Callback;

/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 自定义状态布局需实现该接口
 */
public interface IStatusView {

    void setLoadSir(View view, Callback.OnReloadListener listener);

    /**
     * 显示内容
     */
    void showContent();

    /**
     * 显示进度
     */
    void showProgress();

    /**
     * 显示空页面
     */
    void showEmpty(String msg);

    /**
     * 加载失败
     */
    void showError(String msg);

    /**
     * 网络超时
     */
    void showTimeout();

}
