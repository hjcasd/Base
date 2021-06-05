package com.hjc.library_net.observer

import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.exception.ApiException
import com.hjc.library_net.exception.ExceptionUtils
import com.hjc.library_net.exception.ServerCode
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
abstract class BaseProgressObserver<T>(private val mBaseViewModel: BaseViewModel) : Observer<BaseResponse<T>> {

    private lateinit var mDisposable: Disposable

    override fun onSubscribe(d: Disposable) {
        mBaseViewModel.addDisposable(d)
        mDisposable = d
        mBaseViewModel.showLoading()
    }

    override fun onNext(response: BaseResponse<T>) {
        if (ServerCode.CODE_SUCCESS == response.errorCode) {  //请求成功
            onSuccess(response.data)
        } else {
            //请求成功,Code错误,抛出ApiException
            onError(ApiException(response.errorMsg, response.errorCode))
        }
    }

    override fun onError(e: Throwable) {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        onFailure(e)
    }

    open fun onFailure(e: Throwable) {
        val errorMsg: String = ExceptionUtils.handleException(e)
        ToastUtils.showShort(errorMsg)
    }

    override fun onComplete() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        mBaseViewModel.dismissLoading()
    }

    abstract fun onSuccess(result: T?)

}