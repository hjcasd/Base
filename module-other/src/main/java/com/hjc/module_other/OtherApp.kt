package com.hjc.module_other

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.config.ModuleLifecycleConfig

class OtherApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleLifecycleConfig.getInstance().initModuleAhead(this)
    }
}