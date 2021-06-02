package com.hjc.library_base.base

/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 界面UI显示切换
 */
interface IBaseView {

    /**
     * 显示loading
     */
    fun showLoading()

    /**
     * 隐藏loading
     */
    fun dismissLoading()

    /**
     * 显示内容
     */
    fun showContent()

    /**
     * 显示进度
     */
    fun showProgress()

    /**
     * 显示空页面
     */
    fun showEmpty()

    /**
     * 加载失败
     */
    fun showError()

    /**
     * 网络超时
     */
    fun showTimeout()
}