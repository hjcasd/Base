package com.hjc.baselib.viewmodel

import android.app.Application
import com.hjc.baselib.model.CommonModel

/**
 * @Author: HJC
 * @Date: 2020/6/5 16:45
 * @Description: 通用ViewModel
 */
class CommonViewModel(application: Application) : BaseViewModel(application) {

    protected var mModel: CommonModel = CommonModel()

}