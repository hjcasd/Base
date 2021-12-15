package com.hjc.library_common.module

import com.hjc.library_base.BaseApplication

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:46
 * @Description: 组件初始化类,通过反射机制动态调用每个组件的初始化方法
 */
class ModuleReflect private constructor() {

    companion object {
        fun getInstance(): ModuleReflect {
            return SingleHolder.instance
        }
    }

    private object SingleHolder {
        val instance = ModuleReflect()
    }

    /**
     * 初始化各个模块App
     */
    fun initModuleApp(application: BaseApplication) {
        for (moduleName in ModuleConfig.initModuleNames) {
            try {
                val clazz = Class.forName(moduleName)
                val iModuleInit = clazz.newInstance() as IModuleInit
                iModuleInit.initModuleApp(application)
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