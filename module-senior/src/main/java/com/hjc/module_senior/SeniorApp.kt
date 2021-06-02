package com.hjc.module_senior

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.config.ModuleLifecycleConfig

class SeniorApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
    }
}