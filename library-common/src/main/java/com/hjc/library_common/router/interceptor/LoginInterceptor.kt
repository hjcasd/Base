package com.hjc.library_common.router.interceptor

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.blankj.utilcode.util.LogUtils
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.utils.AccountHelper

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:49
 * @Description: 登录状态拦截器
 */
@Interceptor(name = "login", priority = 8)
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
        val path = postcard.path
        LogUtils.e("path: $path")
        if (!AccountHelper.isLogin) {
            when (path) {
                // 需要登录的页面拦截下来
                RouteOtherPath.URL_DIALOG, RouteOtherPath.URL_SCROLL -> {
                    callback.onInterrupt(null)
                }
                // 不需要登录的页面不拦截
                else -> {
                    callback.onContinue(postcard)
                }
            }
        } else {
            callback.onContinue(postcard)
        }
    }

}