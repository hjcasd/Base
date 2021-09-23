package com.hjc.library_common.router.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.blankj.utilcode.util.LogUtils

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:49
 * @Description: 登录状态拦截器
 */
@Interceptor(name = "登录拦截器", priority = 8)
class LoginInterceptor : IInterceptor {

    /**
     * 上下文
     */
    private lateinit var mContext: Context

    override fun init(context: Context) {
        this.mContext = context
        LogUtils.d("登录拦截器被初始化了")
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        callback.onContinue(postcard)
    }

}