package com.hjc.module_other.viewmodel

import android.app.Application
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel

class OtherViewModel(application: Application) : BaseViewModel(application) {

    fun show() {
        ToastUtils.showShort("Other")
    }
}