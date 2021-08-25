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
 * @Description: 数据返回统一处理Observer基类
 */
abstract class BaseObserver<T>(private val mBaseViewModel: BaseViewModel, private val mIsShowLoading: Boolean = false) : Observer<BaseResponse<T>> {

    private lateinit var mDisposable: Disposable

    private var mBaseResponse: BaseResponse<T>? = null

    override fun onSubscribe(d: Disposable) {
        mBaseViewModel.addDisposable(d)
        mDisposable = d
        if (mIsShowLoading) {
            mBaseViewModel.showLoading()
        }
    }

    override fun onNext(response: BaseResponse<T>) {
        mBaseResponse = response
        if (ServerCode.CODE_SUCCESS == response.errorCode) {  //请求成功
            onSuccess(response.data)
        } else {
            //请求成功,Code错误,抛出ApiException
            onError(ApiException(response.errorMsg, response.errorCode))
        }
    }

    override fun onError(e: Throwable) {
        onFailure(e, mBaseResponse)
    }

    override fun onComplete() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        if (mIsShowLoading){
            mBaseViewModel.dismissLoading()
        }
    }

    abstract fun onSuccess(result: T?)

    abstract fun onFailure(e: Throwable, response: BaseResponse<T>?)
}