package com.hjc.webviewlib.client

import android.graphics.Bitmap
import android.widget.ProgressBar
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebViewClient
 */
class MyWebViewClient(private val mProgressBar: ProgressBar) : WebViewClient() {

    // 防止加载网页时调起系统浏览器
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageStarted(webView: WebView, s: String, bitmap: Bitmap) {
        super.onPageStarted(webView, s, bitmap)
        mProgressBar.visibility = WebView.VISIBLE
    }

    override fun onPageFinished(webView: WebView, s: String) {
        super.onPageFinished(webView, s)
        mProgressBar.visibility = WebView.GONE
    }
}