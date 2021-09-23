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
 * @Description: 带加载框的Observer
 */
abstract class BaseObserver<T>(private val mBaseViewModel: BaseViewModel, private val mIsShowLoading: Boolean = false) :
    Observer<BaseResponse<T>> {

    /**
     * dispose
     */
    private lateinit var mDisposable: Disposable

    override fun onSubscribe(d: Disposable) {
        mBaseViewModel.addDisposable(d)
        mDisposable = d
        if (mIsShowLoading) {
            mBaseViewModel.showLoading()
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
     * 关闭dispose和loading
     */
    private fun dispose() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        if (mIsShowLoading) {
            mBaseViewModel.dismissLoading()
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