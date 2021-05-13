package com.hjc.webviewlib.view

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.hjc.webviewlib.R
import com.hjc.webviewlib.client.MyWebChromeClient
import com.hjc.webviewlib.client.MyWebViewClient
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView

/**
 * @Author: HJC
 * @Date: 2019/7/18 10:42
 * @Description: 自定义X5WebView
 */
class X5WebView(context: Context, attrs: AttributeSet?) : WebView(context, attrs) {

    private lateinit var mProgressBar: ProgressBar

    init {
        initLayout(context)
    }

    private fun initLayout(context: Context) {
        mProgressBar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, dp2px(context))
        mProgressBar.layoutParams = layoutParams

        val drawable = ContextCompat.getDrawable(context, R.drawable.shape_web_progress_bar)
        mProgressBar.progressDrawable = drawable
        addView(mProgressBar)

        initWebViewSettings()

        webViewClient = MyWebViewClient(mProgressBar)
        webChromeClient = MyWebChromeClient(mProgressBar)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        val webSetting = this.settings

        // 启用JavaScript执行。默认的是false。
        webSetting.javaScriptEnabled = true

        // 支持通过JS打开新窗口
        webSetting.javaScriptCanOpenWindowsAutomatically = true

        // 在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
        webSetting.allowFileAccess = true

        // 排版适应屏幕
        webSetting.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS

        // 设置此属性，可任意比例缩放
        webSetting.useWideViewPort = true

        // 启动应用缓存
        webSetting.setAppCacheEnabled(true)
        // 设置应用缓存内容的最大值
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE)
        // 设置缓存模式
        webSetting.cacheMode = WebSettings.LOAD_NO_CACHE

        // DOM存储API是否可用
        webSetting.domStorageEnabled = true

        // 定位是否可用
        webSetting.setGeolocationEnabled(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置安全的来源
            webSetting.mixedContentMode = webSetting.mixedContentMode
        }
    }

    /*
     * 将dp转换为与之相等的px
     */
    private fun dp2px(context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (2.0f * scale + 0.5f).toInt()
    }

}