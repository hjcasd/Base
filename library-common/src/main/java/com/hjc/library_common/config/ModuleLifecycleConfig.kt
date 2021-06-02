package com.hjc.library_common.config

import com.hjc.library_base.BaseApplication
import com.hjc.library_common.module.IModuleInit

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:46
 * @Description: 作为组件生命周期初始化的配置类,通过反射机制,动态调用每个组件初始化逻辑
 */
class ModuleLifecycleConfig private constructor() {

    companion object {
        fun getInstance(): ModuleLifecycleConfig {
            return SingleHolder.instance
        }
    }

    private object SingleHolder {
        val instance = ModuleLifecycleConfig()
    }

    /**
     * 先初始化
     */
    fun initModuleAhead(application: BaseApplication) {
        for (moduleName in ModuleLifecycleReflects.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val init = clazz.newInstance() as IModuleInit
                init.onInitAhead(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 后初始化
     */
    fun initModuleAfter(application: BaseApplication) {
        for (moduleName in ModuleLifecycleReflects.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val init = clazz.newInstance() as IModuleInit
                // 调用初始化方法
                init.onInitAfter(application)
            } catch (e: ClassNotFoundException) {
                e.printStackTrace()
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        }
    }

}