package com.hjc.module_login.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_common.router.ServicePath
import com.hjc.library_common.router.services.ILoginService

/**
 * @Author: HJC
 * @Date: 2021/12/17 13:40
 * @Description: Login模块暴露出的服务
 */
@Route(path = ServicePath.LOGIN_SERVICE, name = "登录服务")
class LoginServiceImpl : ILoginService {

    override fun init(context: Context?) {

    }

    override fun getUsername(): String {
        return "哈哈哈"
    }

}