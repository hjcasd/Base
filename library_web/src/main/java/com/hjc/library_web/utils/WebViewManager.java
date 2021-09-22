package com.hjc.library_web.utils;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.WebView;


/**
 * @Author: HJC
 * @Date: 2021/2/2 17:21
 * @Description: WebView管理类
 */
public final class WebViewManager {

    private WebViewManager() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void clearWebViewCache(Context context) {
        //安卓自带浏览器内核
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookies(null);
        //X5浏览器内核
        com.tencent.smtt.sdk.CookieManager x5CookieManager = com.tencent.smtt.sdk.CookieManager.getInstance();
        x5CookieManager.removeAllCookies(null);
        //删除浏览器相关数据库
        context.deleteDatabase("webview.db");
        context.deleteDatabase("webviewCache.db");

        new WebView(context).clearCache(true);
        new com.tencent.smtt.sdk.WebView(context).clearCache(true);
    }
}
