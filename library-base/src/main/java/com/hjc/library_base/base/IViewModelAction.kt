package com.hjc.library_base.base

import androidx.lifecycle.MutableLiveData

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:28
 * @Description: ViewModel消息接口
 */
interface IViewModelAction {

    /**
     * 显示loading消息
     */
    fun showLoading()

    /**
     * 隐藏loading消息
     */
    fun dismissLoading()

    /**
     * 显示进度消息
     */
    fun showProgress()

    /**
     * 显示内容消息
     */
    fun showContent()

    /**
     * 显示空数据消息
     */
    fun showEmpty(msg: String)

    /**
     * 加载失败消息
     */
    fun showError(msg: String)

    /**
     * 网络超时消息
     */
    fun showTimeout()

    /**
     * 获取消息LiveData
     */
    fun getActionLiveData(): MutableLiveData<BaseActionEvent>

}