package com.hjc.base.widget.web;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.blankj.utilcode.util.ConvertUtils;
import com.hjc.base.R;
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

    public X5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ConvertUtils.dp2px(1));
        mProgressBar.setLayoutParams(layoutParams);

        Drawable drawable = context.getResources().getDrawable(R.drawable.shape_web_progress_bar);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);

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

        // 设置字体默认缩放大小
        webSetting.setTextZoom(100);

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
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(GONE);
                } else {
                    if (mProgressBar.getVisibility() == GONE)
                        mProgressBar.setVisibility(VISIBLE);
                    mProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(webView, newProgress);
            }
        });
    }

//    @Override
//    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
//        AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) mProgressBar.getLayoutParams();
//        lp.x = l;
//        lp.y = t;
//        mProgressBar.setLayoutParams(lp);
//        super.onScrollChanged(l, t, oldl, oldt);
//    }
}
