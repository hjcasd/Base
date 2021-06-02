package com.hjc.library_common.module

import com.hjc.library_base.BaseApplication

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:49
 * @Description: 动态配置组件Application,有需要初始化的组件实现该接口,统一在宿主app 的Application进行初始化
 */
interface IModuleInit {

    /**
     * 需要优先初始化的
     */
    fun onInitAhead(application: BaseApplication): Boolean

    /**
     * 可以后初始化的
     */
    fun onInitAfter(application: BaseApplication): Boolean
}