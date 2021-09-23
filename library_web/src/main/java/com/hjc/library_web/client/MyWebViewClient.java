package com.hjc.library_web.client;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.View;

import com.hjc.library_web.WebLayout;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebViewClient
 */
public class MyWebViewClient extends WebViewClient {

    private final Context mContext;
    private final WebLayout mWebLayout;

    /**
     * 是否重定向页面
     */
    private boolean mIsRedirect = true;

    public MyWebViewClient(Context context, WebLayout webLayout) {
        this.mContext = context;
        this.mWebLayout = webLayout;
    }

    /**
     * url重定向会执行此方法以及点击页面某些链接也会执行此方法
     * true: 表示当前url已经加载完成，即使url还会重定向都不会再进行加载
     * false: 表示此url默认由系统处理，该重定向还是重定向，直到加载完成
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        mIsRedirect = true;

        Log.d("MyWebViewClient", "url: $url");
        if (url.endsWith("apk")) {
            Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mContext.startActivity(viewIntent);
            return true;
        }
        // 防止加载网页时调起系统浏览器
        if (url.startsWith("https") || url.startsWith("http")) {
            webView.loadUrl(url);
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, url);
    }

    @Override
    public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
        super.onPageStarted(webView, s, bitmap);
        mIsRedirect = false;

        webView.setVisibility(View.INVISIBLE);
        mWebLayout.show();
    }

    @Override
    public void onPageFinished(WebView webView, String s) {
        super.onPageFinished(webView, s);
        if (mIsRedirect) {
            return;
        }
        webView.setVisibility(View.VISIBLE);
        mWebLayout.hide();
    }

    /**
     * 处理https请求
     */
    @Override
    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
        sslErrorHandler.proceed();
    }
}
