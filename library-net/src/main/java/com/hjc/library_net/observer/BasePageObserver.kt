package com.hjc.library_net.observer

import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.library_net.exception.ExceptionUtils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
abstract class BasePageObserver<T>(baseViewModel: BaseViewModel, isShowProgress: Boolean = false) : Observer<T> {

    private val mBaseViewModel: BaseViewModel = baseViewModel

    private val mIsShowProgress: Boolean = isShowProgress

    override fun onSubscribe(d: Disposable) {
        mBaseViewModel.addDisposable(d)
        if (mIsShowProgress) {
            mBaseViewModel.showProgress()
        }
    }

    override fun onNext(response: T) {
        mBaseViewModel.showContent()
        onSuccess(response)
    }

    override fun onError(e: Throwable) {
        mBaseViewModel.showError()
        onFailure(e)
    }

    open fun onFailure(e: Throwable) {
        val errorMsg: String = ExceptionUtils.handleException(e)
        ToastUtils.showShort(errorMsg)
    }

    override fun onComplete() {}

    abstract fun onSuccess(result: T)

}