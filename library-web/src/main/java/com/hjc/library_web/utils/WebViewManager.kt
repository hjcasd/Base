package com.hjc.library_web.utils

import android.content.Context
import android.webkit.WebView

/**
 * @Author: HJC
 * @Date: 2021/2/2 17:21
 * @Description: WebView管理类
 */
object WebViewManager {

    /**
     * 清除WebView缓存
     */
    fun clearWebViewCache(context: Context) {
        //安卓自带浏览器内核
        val cookieManager = android.webkit.CookieManager.getInstance()
        cookieManager.removeAllCookies(null)
        //X5浏览器内核
        val x5cookieManager = com.tencent.smtt.sdk.CookieManager.getInstance()
        x5cookieManager.removeAllCookies(null)
        //删除浏览器相关数据库
        context.deleteDatabase("webview.db")
        context.deleteDatabase("webviewCache.db")

        WebView(context).clearCache(true)
        com.tencent.smtt.sdk.WebView(context).clearCache(true)
    }

}

