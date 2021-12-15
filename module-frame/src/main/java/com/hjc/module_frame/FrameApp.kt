package com.hjc.module_frame

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.ModuleReflect

class FrameApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleReflect.getInstance().initModuleApp(this)
    }
}