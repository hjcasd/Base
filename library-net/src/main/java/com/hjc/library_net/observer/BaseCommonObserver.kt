package com.hjc.library_net.observer

import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.exception.ExceptionUtils.handleException

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的Observer
 */
abstract class BaseCommonObserver<T>(baseViewModel: BaseViewModel) :
    BaseObserver<T>(baseViewModel) {

    override fun onFailure(e: Throwable) {
        val errorMsg = handleException(e)
        ToastUtils.showShort(errorMsg)
    }
}