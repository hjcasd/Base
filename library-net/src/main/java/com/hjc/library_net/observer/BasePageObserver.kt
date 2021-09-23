package com.hjc.library_net.observer

import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.exception.ApiException
import com.hjc.library_net.exception.ServerCode
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
abstract class BasePageObserver<T>(private val mBaseViewModel: BaseViewModel, private val mIsShowProgress: Boolean = false) :
    Observer<BaseResponse<T>> {

    /**
     * dispose
     */
    private lateinit var mDisposable: Disposable

    override fun onSubscribe(d: Disposable) {
        mBaseViewModel.addDisposable(d)
        mDisposable = d
        if (mIsShowProgress) {
            mBaseViewModel.showProgress()
        }
    }

    override fun onNext(response: BaseResponse<T>) {
        if (ServerCode.CODE_SUCCESS == response.errorCode) {
            //请求成功,Code正确
            onSuccess(response.data)
        } else {
            //请求成功,Code错误,抛出ApiException
            onFailure(ApiException(response.errorMsg, response.errorCode), response)
        }
    }

    override fun onError(e: Throwable) {
        dispose()
        onFailure(e, null)
    }

    override fun onComplete() {
        dispose()
    }

    /**
     * 关闭dispose
     */
    private fun dispose() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
    }

    /**
     * 成功回调
     */
    abstract fun onSuccess(response: T?)

    /**
     * 错误回调
     */
    abstract fun onFailure(e: Throwable, response: BaseResponse<T>?)

}