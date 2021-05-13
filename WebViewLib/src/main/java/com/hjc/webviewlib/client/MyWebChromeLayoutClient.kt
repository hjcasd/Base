package com.hjc.webviewlib.client

import com.hjc.webviewlib.view.WebProgress
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebChromeClient
 */
class MyWebChromeLayoutClient(private val mWebProgress: WebProgress) : WebChromeClient() {

    override fun onProgressChanged(webView: WebView, newProgress: Int) {
        super.onProgressChanged(webView, newProgress)
        mWebProgress.setWebProgress(newProgress)
    }
}