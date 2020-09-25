package com.hjc.webviewlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @Author: HJC
 * @Date: 2019/7/18 10:42
 * @Description: 自定义X5WebView
 */
public class X5WebView extends WebView {

    private ProgressBar mProgressBar;
    private SpinKitView spinKitView;

    private static final int color = 0XFF1AAF5D;


    public X5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    private void initLayout(Context context){
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        int dp = 2;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, dp2px(context, dp));
        mProgressBar.setLayoutParams(layoutParams);

        Drawable drawable = ContextCompat.getDrawable(context, R.drawable.shape_web_progress_bar);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);

        spinKitView = new SpinKitView(context);
        Sprite wave = new Wave();
        wave.setColor(color);
        spinKitView.setIndeterminateDrawable(wave);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        addView(spinKitView, params);

        initWebViewSettings();
        initClient();
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSetting = this.getSettings();

        // 启用JavaScript执行。默认的是false。
        webSetting.setJavaScriptEnabled(true);

        // 支持通过JS打开新窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);

        // 在File域下，能够执行任意的JavaScript代码，同源策略跨域访问能够对私有目录文件进行访问等
        webSetting.setAllowFileAccess(true);

        // 排版适应屏幕
        webSetting.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS);

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
        setInitialScale(100);
    }

    private void initClient() {
        setWebViewClient(new WebViewClient() {

            // 防止加载网页时调起系统浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                mProgressBar.setVisibility(View.VISIBLE);
                spinKitView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mProgressBar.setVisibility(View.GONE);
                spinKitView.setVisibility(View.GONE);
            }
        });

        setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                super.onProgressChanged(webView, newProgress);
                mProgressBar.setProgress(newProgress);
            }
        });
    }

    /*
     * 将dp转换为与之相等的px
     */
    private int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
