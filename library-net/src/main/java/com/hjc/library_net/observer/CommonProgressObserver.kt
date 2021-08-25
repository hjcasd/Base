package com.hjc.library_net.observer

import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.exception.ExceptionUtils

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的ProgressObserver
 */
abstract class CommonProgressObserver<T>(baseViewModel: BaseViewModel) : BaseProgressObserver<T>(baseViewModel) {

    override fun onFailure(e: Throwable, response: BaseResponse<T>?) {
        val errorMsg = ExceptionUtils.handleException(e)
        ToastUtils.showShort(errorMsg)
    }
}