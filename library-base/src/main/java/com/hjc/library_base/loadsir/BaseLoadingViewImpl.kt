package com.hjc.library_base.loadsir

import android.content.Context
import com.hjc.library_base.base.ILoadingView
import com.hjc.library_base.dialog.LoadingDialog

/**
 * @Author: HJC
 * @Date: 2021/6/25 10:01
 * @Description: 默认加载框实现
 */
class BaseLoadingViewImpl(context: Context) : ILoadingView {

    /**
     * LoadingDialog
     */
    private var mLoadingDialog: LoadingDialog? = null

    init {
        mLoadingDialog = LoadingDialog.Builder(context)
            .setMessage("加载中...")
            .setCancelable(true)
            .setCancelOutside(false)
            .create()
    }

    override fun showLoading() {
        mLoadingDialog?.show()
    }

    override fun dismissLoading() {
        mLoadingDialog?.dismiss()
    }

}