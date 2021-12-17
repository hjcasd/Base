package com.hjc.library_common.router.services

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:52
 * @Description: App登录服务接口,登录模块通过实现该接口暴露出登录相关服务给外部使用
 */
interface ILoginService : IProvider {

    /**
     * 获取用户名
     */
    fun getUsername(): String

}