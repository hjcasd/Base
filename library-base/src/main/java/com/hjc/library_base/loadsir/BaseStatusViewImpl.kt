package com.hjc.library_base.loadsir

import android.annotation.SuppressLint
import android.view.View
import com.hjc.library_base.base.IStatusView
import com.hjc.library_base.loadsir.callback.DefaultEmptyCallback
import com.hjc.library_base.loadsir.callback.DefaultErrorCallback
import com.hjc.library_base.loadsir.callback.DefaultProgressCallback
import com.hjc.library_base.loadsir.callback.DefaultTimeoutCallback
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
open class BaseStatusViewImpl : IStatusView {

    /**
     * LoadSir服务
     */
    private var mLoadService: LoadService<*>? = null

    override fun setLoadSir(view: View?, listener: Callback.OnReloadListener?) {
        val loadSir = LoadSir.getDefault()
        mLoadService = loadSir.register(view, listener)
    }

    @SuppressLint("CheckResult")
    override fun showContent() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showSuccess() }
    }

    override fun showProgress() {
        mLoadService?.showCallback(DefaultProgressCallback::class.java)
    }

    @SuppressLint("CheckResult")
    override fun showEmpty(msg: String) {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showCallback(DefaultEmptyCallback::class.java) }
    }

    @SuppressLint("CheckResult")
    override fun showError(msg: String) {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showCallback(DefaultErrorCallback::class.java) }
    }

    @SuppressLint("CheckResult")
    override fun showTimeout() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showCallback(DefaultTimeoutCallback::class.java) }
    }

}