package com.hjc.library_common.config

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:48
 * @Description: 组件生命周期初始化类的管理者,在这里注册需要初始化的组件,通过反射动态调用各个组件的初始化方法
 */
object ModuleLifecycleReflects {

    /**
     * 基础库
     */
    private const val CommonInit = "com.hjc.library_common.CommonModuleInit"

    /**
     * module数组
     */
    var initModuleNames = arrayOf(CommonInit)

}