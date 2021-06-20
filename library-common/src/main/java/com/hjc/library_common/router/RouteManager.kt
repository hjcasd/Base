package com.hjc.library_common.router

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.core.app.ActivityOptionsCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.utils.ClickUtils
import com.hjc.library_common.global.GlobalKey
import com.hjc.library_common.router.path.RouteMainPath

/**
 * @Author: HJC
 * @Date: 2020/8/24 14:55
 * @Description: 页面路由管理
 */
object RouteManager {

    /**
     * 页面跳转
     *
     * @param path 要跳转页面对应的路由url
     */
    fun jump(path: String?) {
        ARouter.getInstance()
            .build(path)
            .navigation()
    }

    /**
     * 页面跳转(bundle)
     *
     * @param path   要跳转页面对应的路由url
     * @param bundle 传递的参数
     */
    fun jumpWithBundle(path: String?, bundle: Bundle?) {
        ARouter.getInstance()
            .build(path)
            .withBundle(GlobalKey.ROUTER_PARAMS, bundle)
            .navigation()
    }

    /**
     * 页面跳转(bundle)
     *
     * @param path      要跳转页面对应的路由url
     * @param enterAnim 进入动画
     * @param exitAnim  退出动画
     * @param context   对应页面
     */
    fun jumpWithTransition(context: Context, path: String?, enterAnim: Int, exitAnim: Int) {
        ARouter.getInstance()
            .build(path)
            .withTransition(enterAnim, exitAnim)
            .navigation(context)
    }

    /**
     * 页面跳转(scene)
     *
     * @param context 当前页面
     * @param path    要跳转页面对应的路由url
     * @param bundle  传递的参数
     * @param compat  转场动画
     */
    fun jumpWithScene(
        context: Context?,
        path: String?,
        bundle: Bundle?,
        compat: ActivityOptionsCompat?
    ) {
        ARouter.getInstance()
            .build(path)
            .withBundle(GlobalKey.ROUTER_PARAMS, bundle)
            .withOptionsCompat(compat)
            .navigation(context)
    }

    /**
     * 页面跳转
     *
     * @param context     对应页面
     * @param path        要跳转页面对应的路由url
     * @param bundle      传递的参数
     * @param requestCode code码
     */
    fun jumpWithCode(context: Activity?, path: String?, bundle: Bundle?, requestCode: Int) {
        ARouter.getInstance()
            .build(path)
            .withBundle(GlobalKey.ROUTER_PARAMS, bundle)
            .navigation(context, requestCode)
    }

    /**
     * 页面跳转(web)
     *
     * @param title 标题
     * @param url   链接地址
     */
    fun jumpToWeb(title: String?, url: String?) {
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!")
            return
        }
        if (StringUtils.isEmpty(url)) {
            ToastUtils.showShort("链接地址不能为空")
            return
        }
        ARouter.getInstance()
            .build(RouteMainPath.URL_WEB)
            .withString("title", title)
            .withString("url", url)
            .navigation()
    }
}