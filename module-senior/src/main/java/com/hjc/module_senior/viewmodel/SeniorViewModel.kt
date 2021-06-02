package com.hjc.module_senior.viewmodel

import android.app.Application
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.viewmodel.BaseViewModel

class SeniorViewModel(application: Application) : BaseViewModel(application) {

    fun show() {
        ToastUtils.showShort("Senior")
    }
}