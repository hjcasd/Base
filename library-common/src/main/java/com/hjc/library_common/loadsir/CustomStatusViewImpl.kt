package com.hjc.library_common.loadsir

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import com.hjc.library_base.R
import com.hjc.library_base.loadsir.BaseStatusViewImpl
import com.hjc.library_common.loadsir.callback.CustomEmptyCallback
import com.hjc.library_common.loadsir.callback.CustomErrorCallback
import com.hjc.library_common.loadsir.callback.CustomShimmerCallback
import com.hjc.library_common.loadsir.callback.CustomTimeoutCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2021/8/25 14:33
 * @Description: 自定义状态布局实现
 */
class CustomStatusViewImpl : BaseStatusViewImpl() {

    protected var mLoadService: LoadService<*>? = null

    override fun setLoadSir(view: View?, listener: Callback.OnReloadListener?) {
        val loadSir = LoadSir.Builder()
            .addCallback(CustomShimmerCallback())
            .addCallback(CustomEmptyCallback())
            .addCallback(CustomErrorCallback())
            .addCallback(CustomTimeoutCallback())
            .build()
        mLoadService = loadSir.register(view, listener)
    }

    override fun showProgress() {
        mLoadService?.showCallback(CustomShimmerCallback::class.java)
    }

    @SuppressLint("CheckResult")
    override fun showContent() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { mLoadService?.showSuccess() }
    }

    override fun showEmpty(msg: String) {
        mLoadService?.setCallBack(CustomEmptyCallback::class.java) { _, view ->
            val tvEmpty = view.findViewById<TextView>(R.id.tv_empty)
            tvEmpty.text = msg
        }
        mLoadService?.showCallback(CustomEmptyCallback::class.java)
    }

    override fun showError(msg: String) {
        mLoadService?.setCallBack(CustomErrorCallback::class.java) { _, view ->
            val tvError = view.findViewById<TextView>(R.id.tv_error)
            tvError.text = msg
        }
        mLoadService?.showCallback(CustomErrorCallback::class.java)
    }

    override fun showTimeout() {
        mLoadService?.showCallback(CustomTimeoutCallback::class.java)
    }
}