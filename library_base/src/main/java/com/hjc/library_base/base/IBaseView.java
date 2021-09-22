package com.hjc.library_base.base;


/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 界面UI显示切换
 */
public interface IBaseView {

    /**
     * 显示Loading
     */
    void showLoading();

    /**
     * 隐藏Loading
     */
    void dismissLoading();

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
    void showEmpty();

    /**
     * 加载失败
     */
    void showError();

    /**
     * 网络超时
     */
    void showTimeout();

}
