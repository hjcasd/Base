package com.hjc.library_common.global

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:48
 * @Description: 全局的存储key,单个组件的可以单个组件自行在各自的组件中定义
 */
object GlobalKey {

    /**
     * 用户信息
     */
    const val USER_INFO = "user_info"

    /**
     * ARouter传参key
     */
    const val ROUTER_PARAMS = "router_params"

    /**
     * 登录页面拦截的路由路径
     */
    const val LOGIN_ROUTER_PATH = "login_router_path"

}