package com.hjc.module_login

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.ModuleReflect

class LoginApp : BaseApplication(){

    override fun onCreate() {
        super.onCreate()
        ModuleReflect.getInstance().initModuleApp(this)
    }
}