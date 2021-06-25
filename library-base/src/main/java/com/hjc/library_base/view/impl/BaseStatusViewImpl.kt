package com.hjc.library_base.view.impl

import android.annotation.SuppressLint
import android.view.View
import com.hjc.library_base.loadsir.EmptyCallback
import com.hjc.library_base.loadsir.ErrorCallback
import com.hjc.library_base.loadsir.ProgressCallback
import com.hjc.library_base.loadsir.TimeoutCallback
import com.hjc.library_base.view.IStatusView
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2021/6/25 10:02
 * @Description: 默认状态布局实现
 */
class BaseStatusViewImpl : IStatusView {

    private var mLoadService: LoadService<*>? = null

    override fun setLoadSir(view: View?, listener: Callback.OnReloadListener) {
        mLoadService = LoadSir.getDefault().register(view) { v: View? -> listener.onReload(v) }
    }

    @SuppressLint("CheckResult")
    override fun showContent() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showSuccess() }
    }

    override fun showProgress() {
        mLoadService?.showCallback(ProgressCallback::class.java)
    }

    @SuppressLint("CheckResult")
    override fun showEmpty() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showCallback(EmptyCallback::class.java) }
    }

    @SuppressLint("CheckResult")
    override fun showError() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showCallback(ErrorCallback::class.java) }
    }

    @SuppressLint("CheckResult")
    override fun showTimeout() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showCallback(TimeoutCallback::class.java) }
    }
}