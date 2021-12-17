package com.hjc.library_common.router

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:51
 * @Description: 各个组件需要暴露给外部的服务路由路径
 */
object ServicePath {

    /**
     * service路径
     */
    private const val SERVICE = "/service"

    /**
     * 用户登录状态
     */
    const val LOGIN_SERVICE = "$SERVICE/login"

}