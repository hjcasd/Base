package com.hjc.library_web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.github.ybq.android.spinkit.SpinKitView;
import com.hjc.library_web.client.MyWebChromeClient;
import com.hjc.library_web.client.MyWebViewClient;
import com.hjc.library_web.view.WebProgressView;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;

/**
 * @Author: HJC
 * @Date: 2019/7/18 10:42
 * @Description: 自定义X5WebLayout
 */
public class WebLayout extends LinearLayout {

    private final Context mContext;

    private WebProgressView mWebProgress;
    private WebView mWebView;
    private SpinKitView mLoadingView;

    private static final String WEB_PROGRESS_COLOR = "#ff00ff00";

    public WebLayout(Context context) {
        this(context, null);
    }

    public WebLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        initData();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.web_layout, this);
        mWebProgress = findViewById(R.id.web_progress_view);
        mWebView = findViewById(R.id.web_view);
        mLoadingView = findViewById(R.id.loading_view);

        mWebProgress.setColor(Color.parseColor(WEB_PROGRESS_COLOR));
    }

    private void initData() {
        initWebViewSettings();

        mWebView.setWebViewClient(new MyWebViewClient(mContext, this));
        mWebView.setWebChromeClient(new MyWebChromeClient(this));
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSettings = mWebView.getSettings();

        // 启用JavaScript执行。默认的是false。
        webSettings.setJavaScriptEnabled(true);

        // 支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
        webSettings.setAllowFileAccess(true);

        // 排版适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        // 设置自适应屏幕
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);

        // 启动应用缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 设置应用缓存内容的最大值
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE);

        // DOM存储API是否可用
        webSettings.setDomStorageEnabled(true);

        // 定位是否可用
        webSettings.setGeolocationEnabled(true);
    }

    /**
     * 设置进度条显示进度
     */
    public void setProgress(int newProgress) {
        mWebProgress.setWebProgress(newProgress);
    }

    /**
     * 显示进度条
     */
    public void show() {
        mWebProgress.show();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏进度条
     */
    public void hide() {
        mWebProgress.hide();
        mLoadingView.setVisibility(View.GONE);
    }

    /**
     * 获取当前WebView
     *
     * @return WebView
     */
    public WebView getWebView() {
        return mWebView;
    }
}
