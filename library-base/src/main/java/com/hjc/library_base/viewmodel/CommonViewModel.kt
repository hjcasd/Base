package com.hjc.library_base.viewmodel

import android.app.Application
import com.hjc.library_base.model.CommonModel

/**
 * @Author: HJC
 * @Date: 2020/6/5 16:45
 * @Description: 通用ViewModel
 */
open class CommonViewModel(application: Application) : BaseViewModel(application) {

    private var mModel: CommonModel = CommonModel()

    fun show() {
        mModel.show()
    }

}