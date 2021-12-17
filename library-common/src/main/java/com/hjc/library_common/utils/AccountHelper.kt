package com.hjc.library_common.utils

import com.blankj.utilcode.util.SPUtils

/**
 * @Author: HJC
 * @Date: 2019/2/20 15:39
 * @Description: 账户管理类
 */
object AccountHelper {

    private const val KEY_IS_LOGIN = "isLogin"

    /**
     * 用户是否登录
     */
    var isLogin: Boolean
        get() = SPUtils.getInstance().getBoolean(KEY_IS_LOGIN, false)
        set(value) {
            SPUtils.getInstance().put(KEY_IS_LOGIN, value)
        }

}