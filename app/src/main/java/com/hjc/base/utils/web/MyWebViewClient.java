package com.hjc.base.utils.web;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Author: HJC
 * @Date: 2019/3/8 11:15
 * @Description: 自定义WebViewClient
 */
public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return super.shouldOverrideUrlLoading(view, url);
    }

    // 视频全屏播放按返回页面被放大的问题
    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
        if (newScale - oldScale > 7) {
            //异常放大，缩回去
            view.setInitialScale((int) (oldScale / newScale * 100));
        }
    }
}

