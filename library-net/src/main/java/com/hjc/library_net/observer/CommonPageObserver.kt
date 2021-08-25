package com.hjc.library_net.observer

import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.exception.ExceptionUtils

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的PageObserver
 */
abstract class CommonPageObserver<T>(baseViewModel: BaseViewModel) : BasePageObserver<T>(baseViewModel) {

    override fun onFailure(e: Throwable, result: T?) {
        val errorMsg: String = ExceptionUtils.handleException(e)
        ToastUtils.showShort(errorMsg)
    }
}