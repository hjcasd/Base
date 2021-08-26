package com.hjc.library_web.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.hjc.library_web.MyWebChromeClient
import com.hjc.library_web.MyWebViewClient
import com.hjc.library_web.R
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

/**
 * @Author: HJC
 * @Date: 2019/7/18 10:42
 * @Description: 自定义X5WebView
 */
class WebLayout @JvmOverloads constructor(
    private val mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    mContext, attrs, defStyleAttr
) {
    private lateinit var mWebProgress: WebProgress
    private lateinit var mWebView: WebView
    private lateinit var mLoadingView: ProgressBar

    companion object {
        const val webProgressColor = "#ff00ff00"
    }

    init {
        initView()
        initData()
    }

    private fun initView() {
        LayoutInflater.from(mContext).inflate(R.layout.web_layout, this)
        mWebProgress = findViewById(R.id.web_progress)
        mWebView = findViewById(R.id.web_view)
        mLoadingView = findViewById(R.id.loading_view)

        mWebProgress.setColor(Color.parseColor(webProgressColor))
    }

    fun getWebView(): WebView {
        return mWebView
    }

    private fun initData() {
        initWebViewSettings()

        mWebView.webViewClient = MyWebViewClient(mContext, mWebProgress, mLoadingView)
        mWebView.webChromeClient = MyWebChromeClient(mWebProgress)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        val webSetting = mWebView.settings

        // 启用JavaScript执行。默认的是false。
        webSetting.javaScriptEnabled = true

        // 支持通过JS打开新窗口
        webSetting.javaScriptCanOpenWindowsAutomatically = true

        // 在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
        webSetting.allowFileAccess = true

        // 排版适应屏幕
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS

        // 设置自适应屏幕
        webSetting.useWideViewPort = true
        // 缩放至屏幕的大小
        webSetting.loadWithOverviewMode = true

        // 启动应用缓存
        webSetting.setAppCacheEnabled(true)
        // 设置应用缓存内容的最大值
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        // 设置缓存模式
        webSetting.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK

        // DOM存储API是否可用
        webSetting.domStorageEnabled = true

        // 定位是否可用
        webSetting.setGeolocationEnabled(true)

        //设置安全的来源
        webSetting.mixedContentMode = webSetting.mixedContentMode
    }

}