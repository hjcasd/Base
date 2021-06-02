package com.hjc.library_base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hjc.library_base.base.BaseActionEvent
import com.hjc.library_base.base.IViewModelAction

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:15
 * @Description: ViewModel 基类
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application),
    IViewModelAction {

    private val actionLiveData: MutableLiveData<BaseActionEvent> = MutableLiveData()

    override fun getActionLiveData(): MutableLiveData<BaseActionEvent> {
        return actionLiveData
    }

    override fun showLoading() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG)
        actionLiveData.value = baseActionEvent
    }

    override fun dismissLoading() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG)
        actionLiveData.value = baseActionEvent
    }

    override fun showProgress() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_PROGRESS)
        actionLiveData.value = baseActionEvent
    }

    override fun showContent() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_CONTENT)
        actionLiveData.value = baseActionEvent
    }

    override fun showEmpty() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_EMPTY)
        actionLiveData.value = baseActionEvent
    }

    override fun showError() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_ERROR)
        actionLiveData.value = baseActionEvent
    }

    override fun showTimeout() {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_TIMEOUT)
        actionLiveData.value = baseActionEvent
    }

}