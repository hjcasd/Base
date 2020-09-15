package com.hjc.baselib.base;


/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 界面UI显示切换
 */
public interface IBaseView {
    /**
     * 显示内容
     */
    void showContent();

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 显示空页面
     */
    void showEmpty();

    /**
     * 加载失败
     */
    void showError();

}
