package com.hjc.module_home

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.ModuleReflect

class HomeApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleReflect.getInstance().initModuleApp(this)
    }
}