package com.hjc.module_other

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.IModuleInit
import tech.oom.idealrecorder.IdealRecorder

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:32
 * @Description: 通用库初始化操作
 */
class OtherModuleInit : IModuleInit {

    override fun initModuleApp(application: BaseApplication) {
        initRecord(application)
    }

    /**
     * 初始化录音
     */
    private fun initRecord(application: BaseApplication) {
        IdealRecorder.getInstance().init(application)
    }

}