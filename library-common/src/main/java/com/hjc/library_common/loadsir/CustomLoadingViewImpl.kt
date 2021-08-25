package com.hjc.library_common.loadsir

import android.content.Context
import com.hjc.library_base.base.ILoadingView
import com.hjc.library_base.dialog.LoadingDialog

/**
 * @Author: HJC
 * @Date: 2021/6/25 10:01
 * @Description: 自定义加载框实现
 */
class CustomLoadingViewImpl(context: Context) : ILoadingView {

    private var mLoadingDialog: LoadingDialog? = null

    init {
        mLoadingDialog = LoadingDialog.Builder(context)
            .setMessage("加载中，请稍后...")
            .setCancelable(true)
            .setCancelOutside(false)
            .create()
    }

    override fun showLoading() {
        mLoadingDialog?.show()
    }

    override fun dismissLoading() {
        mLoadingDialog?.dismissDialog()
    }
}