package com.hjc.webviewlib.client

import android.widget.ProgressBar
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebChromeClient
 */
class MyWebChromeClient(private val mProgressBar: ProgressBar) : WebChromeClient() {

    override fun onProgressChanged(webView: WebView, newProgress: Int) {
        super.onProgressChanged(webView, newProgress)
        mProgressBar.progress = newProgress
    }
}