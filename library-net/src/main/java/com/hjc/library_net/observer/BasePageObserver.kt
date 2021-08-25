package com.hjc.library_net.observer

import com.hjc.library_base.viewmodel.BaseViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
abstract class BasePageObserver<T>(private val mBaseViewModel: BaseViewModel, private val mIsShowProgress: Boolean = false) : Observer<T> {

    private lateinit var mDisposable: Disposable

    private var mResponse: T? = null

    override fun onSubscribe(d: Disposable) {
        mBaseViewModel.addDisposable(d)
        mDisposable = d
        if (mIsShowProgress) {
            mBaseViewModel.showProgress()
        }
    }

    override fun onNext(response: T) {
        mResponse = response
        mBaseViewModel.showContent()
        onSuccess(response)
    }

    override fun onError(e: Throwable) {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
        onFailure(e, mResponse)
    }

    override fun onComplete() {
        if (!mDisposable.isDisposed) {
            mDisposable.dispose()
        }
    }

    abstract fun onSuccess(result: T)

    abstract fun onFailure(e: Throwable, result: T?)
}