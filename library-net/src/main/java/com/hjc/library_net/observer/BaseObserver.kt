package com.hjc.library_net.observer

import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.exception.ApiException
import com.hjc.library_net.exception.ServerCode
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 数据返回统一处理Observer基类
 */
abstract class BaseObserver<T> : Observer<BaseResponse<T>> {

    private var mDisposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        mDisposable = d
    }

    override fun onNext(response: BaseResponse<T>) {
        if (ServerCode.CODE_SUCCESS == response.errorCode) {  //请求成功
            onSuccess(response.data)
        } else { //请求成功,Code错误,抛出ApiException
            onError(ApiException(response.errorMsg, response.errorCode))
        }
    }

    override fun onError(e: Throwable) {
        mDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        onFailure(e)
    }

    override fun onComplete() {
        mDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    abstract fun onSuccess(result: T?)

    abstract fun onFailure(e: Throwable)
}