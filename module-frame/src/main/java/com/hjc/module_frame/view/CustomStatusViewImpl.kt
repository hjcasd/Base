package com.hjc.module_frame.view

import android.view.View
import com.hjc.library_common.view.impl.CommonStatusViewImpl
import com.hjc.module_frame.view.callback.CustomEmptyCallback
import com.hjc.module_frame.view.callback.CustomErrorCallback
import com.hjc.module_frame.view.callback.CustomProgressCallback
import com.hjc.module_frame.view.callback.CustomTimeoutCallback
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadSir


class CustomStatusViewImpl : CommonStatusViewImpl() {

    override fun setLoadSir(view: View?, listener: Callback.OnReloadListener) {
        val loadSir = LoadSir.Builder()
            .addCallback(CustomProgressCallback())
            .addCallback(CustomEmptyCallback())
            .addCallback(CustomErrorCallback())
            .addCallback(CustomTimeoutCallback())
            .build()
        mLoadService = loadSir.register(view) { v: View? -> listener.onReload(v) }
    }

    override fun showProgress() {
        mLoadService?.showCallback(CustomProgressCallback::class.java)
    }

    override fun showEmpty() {
        mLoadService?.showCallback(CustomEmptyCallback::class.java)
    }

    override fun showError() {
        mLoadService?.showCallback(CustomErrorCallback::class.java)
    }

    override fun showTimeout() {
        mLoadService?.showCallback(CustomTimeoutCallback::class.java)
    }
}