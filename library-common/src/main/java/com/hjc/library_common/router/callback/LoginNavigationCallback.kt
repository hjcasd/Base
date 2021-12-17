package com.hjc.library_common.router.callback

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.hjc.library_common.R
import com.hjc.library_common.global.GlobalKey
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteLoginPath

/**
 * @Author: HJC
 * @Date: 2021/12/16 21:22
 * @Description: 其他页面跳转被登录拦截后的回调
 */
class LoginNavigationCallback constructor(private val mContext: Context) : NavigationCallback {

    override fun onFound(postcard: Postcard?) {

    }

    override fun onLost(postcard: Postcard?) {

    }

    override fun onArrival(postcard: Postcard?) {

    }

    override fun onInterrupt(postcard: Postcard?) {
        val path = postcard?.path
        val bundle = postcard?.extras
        bundle?.putString(GlobalKey.LOGIN_ROUTER_PATH, path)
        // 被登录拦截了下来了
        // 需要调转到登录页面，把参数跟被登录拦截下来的路径传递给登录页面，登录成功后再进行跳转被拦截的页面
        RouteManager.jumpWithTransition(
            mContext,
            RouteLoginPath.URL_LOGIN_ACTIVITY,
            R.anim.common_login_open_enter,
            R.anim.common_login_open_exit,
            bundle
        )
    }

}