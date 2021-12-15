package com.hjc.module_senior

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.ModuleReflect

class SeniorApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleReflect.getInstance().initModuleApp(this)
    }
}