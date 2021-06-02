package com.hjc.library_base.base

import androidx.lifecycle.MutableLiveData

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:28
 * @Description: ViewModel基础接口
 */
interface IViewModelAction {

    fun showLoading()

    fun dismissLoading()

    fun showProgress()

    fun showContent()

    fun showEmpty()

    fun showError()

    fun showTimeout()

    fun getActionLiveData(): MutableLiveData<BaseActionEvent>
}