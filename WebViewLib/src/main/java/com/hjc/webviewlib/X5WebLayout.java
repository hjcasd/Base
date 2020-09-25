package com.hjc.webviewlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

import com.github.ybq.android.spinkit.SpinKitView;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @Author: HJC
 * @Date: 2019/7/18 10:42
 * @Description: 自定义X5WebView
 */
public class X5WebLayout extends LinearLayout {

    private Context mContext;

    private ProgressBar progressBar;
    private WebView webView;
    private SpinKitView spinKitView;

    public X5WebLayout(Context context) {
        this(context, null);
    }

    public X5WebLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public X5WebLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
        initData();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.layout_web, this);

        progressBar = findViewById(R.id.progress_bar);
        webView = findViewById(R.id.web_view);
        spinKitView = findViewById(R.id.spin_kit_view);
    }

    public void loadUrl(String url) {
        webView.loadUrl(url);
    }

    public void reload() {
        webView.reload();
    }

    public boolean canGoBack() {
        return webView.canGoBack();
    }

    public void goBack() {
        webView.goBack();
    }

    private void initData() {
        initWebViewSettings();

        webView.setWebViewClient(new WebViewClient() {

            // 防止加载网页时调起系统浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                webView.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                spinKitView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                webView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                spinKitView.setVisibility(View.GONE);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                progressBar.setProgress(newProgress);
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = webView.getSettings();

        // 启用JavaScript执行。默认的是false。
        webSetting.setJavaScriptEnabled(true);

        // 支持通过JS打开新窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);

        // 在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
        webSetting.setAllowFileAccess(true);

        // 排版适应屏幕
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        // 是否应该支持使用其屏幕缩放控件和手势缩放
        webSetting.setSupportZoom(true);

        // 进行控制缩放
        webSetting.setBuiltInZoomControls(true);

        // 设置此属性，可任意比例缩放
        webSetting.setUseWideViewPort(true);

        // 设置WebView是否支持多窗口,如果为true需要实现onCreateWindow(WebView, boolean, boolean, Message)
        webSetting.setSupportMultipleWindows(true);

        // 启动应用缓存
        webSetting.setAppCacheEnabled(true);
        // 设置应用缓存内容的最大值
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        // 设置缓存模式
        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);

        // DOM存储API是否可用
        webSetting.setDomStorageEnabled(true);

        // 定位是否可用
        webSetting.setGeolocationEnabled(true);

        //设置是否支持插件
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //设置安全的来源
            webSetting.setMixedContentMode(webSetting.getMixedContentMode());
        }

        // 不缩放
        webView.setInitialScale(100);
    }

}
