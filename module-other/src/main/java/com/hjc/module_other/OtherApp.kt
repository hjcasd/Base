package com.hjc.module_other

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.ModuleReflect

class OtherApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleReflect.getInstance().initModuleApp(this)
    }
}