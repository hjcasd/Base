package com.hjc.library_common.module

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:48
 * @Description: 组件配置类,注册需要初始化的组件类名
 */
object ModuleConfig {

    /**
     * 通用初始化操作
     */
    private const val COMMON_APP_INIT = "com.hjc.library_common.CommonModuleInit"

    /**
     * 其他模块初始化操作
     */
    private const val OTHER_APP_INIT = "com.hjc.module_other.OtherModuleInit"


    /**
     * 组件初始化操作的数组
     */
    val initModuleNames = arrayOf(COMMON_APP_INIT, OTHER_APP_INIT)

}