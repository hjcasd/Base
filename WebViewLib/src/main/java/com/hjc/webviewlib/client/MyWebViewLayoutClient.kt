package com.hjc.webviewlib.client

import android.graphics.Bitmap
import android.view.View
import android.widget.ProgressBar
import com.hjc.webviewlib.view.WebProgress
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebViewClient
 */
class MyWebViewLayoutClient(
    private val mWebProgress: WebProgress,
    private val mLoadingView: ProgressBar
) : WebViewClient() {

    // 防止加载网页时调起系统浏览器
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        return true
    }

    override fun onPageStarted(webView: WebView, s: String, bitmap: Bitmap) {
        super.onPageStarted(webView, s, bitmap)
        webView.visibility = View.INVISIBLE
        mWebProgress.show()
        mLoadingView.visibility = WebView.VISIBLE
    }

    override fun onPageFinished(webView: WebView, s: String) {
        super.onPageFinished(webView, s)
        webView.visibility = View.VISIBLE
        mWebProgress.hide()
        mLoadingView.visibility = WebView.GONE
    }
}