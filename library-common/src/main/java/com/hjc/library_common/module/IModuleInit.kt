package com.hjc.library_common.module

import com.hjc.library_base.BaseApplication

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:49
 * @Description: 组件初始化接口,组件Application中有初始化操作逻辑的需实现该接口
 */
interface IModuleInit {

    /**
     * 初始化组件Application的操作
     */
    fun initModuleApp(application: BaseApplication)

}