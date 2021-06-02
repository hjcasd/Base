package com.hjc.library_web

import com.hjc.library_web.view.WebProgress
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback
import com.tencent.smtt.sdk.WebBackForwardList
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebChromeClient
 */
class MyWebChromeClient(private val mWebProgress: WebProgress) : WebChromeClient() {

    override fun onProgressChanged(webView: WebView?, newProgress: Int) {
        super.onProgressChanged(webView, newProgress)
        mWebProgress.setWebProgress(newProgress)
    }

    override fun onReceivedTitle(webView: WebView?, title: String?) {
        super.onReceivedTitle(webView, title)
        getWebTitle(webView)
    }

    /**
     * 获取网页标题
     */
    private fun getWebTitle(webView: WebView?): String {
        val forwardList: WebBackForwardList = webView!!.copyBackForwardList()
        val item = forwardList.currentItem
        return item.title
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissionsCallback?
    ) {
        callback?.invoke(origin, true, false)
        super.onGeolocationPermissionsShowPrompt(origin, callback)
    }
}