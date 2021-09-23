package com.hjc.library_common.services.config

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:51
 * @Description: 各个组件需要暴露给外部的service名称 配置
 */
object ServicesConfig {

    /**
     * service路径
     */
    private const val SERVICE = "/service"

    /**
     * 用户模块
     */
    object User {

        /**
         * 用户登录状态
         */
        const val LOGIN_SERVICE = "$SERVICE/login"

    }

}