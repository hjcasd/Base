package com.hjc.library_web

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.hjc.library_web.view.WebProgress
import com.tencent.smtt.export.external.interfaces.SslError
import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebViewClient
 */
class MyWebViewClient(
    private val mContext: Context,
    private val mWebProgress: WebProgress,
    private val mLoadingView: ProgressBar
) : WebViewClient() {

    /**
     * 是否重定向页面
     */
    private var mIsRedirect = true

    /**
     * url重定向会执行此方法以及点击页面某些链接也会执行此方法
     * true: 表示当前url已经加载完成，即使url还会重定向都不会再进行加载 false 表示此url默认由系统处理，该重定向还是重定向，直到加载完成
     */
    override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
        mIsRedirect = true

        Log.d("MyWebViewClient", "url: $url")
        if (url.endsWith("apk")) {
            val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            mContext.startActivity(viewIntent)
            return true
        }
        // 防止加载网页时调起系统浏览器
        if (url.startsWith("https") || url.startsWith("http")) {
            view?.loadUrl(url)
            return true
        }
        return super.shouldOverrideUrlLoading(view, url)
    }

    override fun onPageStarted(webView: WebView?, s: String?, bitmap: Bitmap?) {
        super.onPageStarted(webView, s, bitmap)
        mIsRedirect = false

        webView?.visibility = View.INVISIBLE
        mWebProgress.show()
        mLoadingView.visibility = View.VISIBLE
    }

    override fun onPageFinished(webView: WebView?, s: String?) {
        super.onPageFinished(webView, s)
        if (mIsRedirect) {
            return
        }
        webView?.visibility = View.VISIBLE
        mWebProgress.hide()
        mLoadingView.visibility = View.GONE
    }

    // 处理https请求
    override fun onReceivedSslError(
        webView: WebView?,
        handler: SslErrorHandler?,
        error: SslError?
    ) {
        super.onReceivedSslError(webView, handler, error)
        handler?.proceed()
    }
}