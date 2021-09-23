package com.hjc.library_common.services

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:52
 * @Description: app登录相关信息
 */
interface ILoginService : IProvider {

    /**
     * 保存登录状态
     *
     * @param status 登录状态
     * @return 返回当前登录状态
     */
    fun saveStatus(status: Boolean): Boolean

    /**
     * 是否登录
     *
     * @return 是否登录
     */
    val isLogin: Boolean

}