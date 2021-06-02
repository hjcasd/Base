package com.hjc.module_home

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.config.ModuleLifecycleConfig

class HomeApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
    }
}