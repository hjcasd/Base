package com.hjc.library_base.base;

/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 自定义加载框需实现该接口
 */
public interface ILoadingView {

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 隐藏loading
     */
    void dismissLoading();

}
