package com.hjc.module_main

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.ModuleReflect

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:17
 * @Description: Application
 */
class MainApp : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        ModuleReflect.getInstance().initModuleApp(this)
    }
}