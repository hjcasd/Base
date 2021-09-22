package com.hjc.library_web.client;

import com.hjc.library_web.WebLayout;
import com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback;
import com.tencent.smtt.sdk.WebBackForwardList;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebHistoryItem;
import com.tencent.smtt.sdk.WebView;


/**
 * @Author: HJC
 * @Date: 2021/1/9 15:31
 * @Description: 自定义WebChromeClient
 */
public class MyWebChromeClient extends WebChromeClient {

    private final WebLayout mWebLayout;

    public MyWebChromeClient(WebLayout webLayout) {
        this.mWebLayout = webLayout;
    }

    @Override
    public void onProgressChanged(WebView webView, int newProgress) {
        super.onProgressChanged(webView, newProgress);
        mWebLayout.setProgress(newProgress);
    }

    @Override
    public void onReceivedTitle(WebView webView, String title) {
        super.onReceivedTitle(webView, title);
//        getWebTitle(webView);
    }

    @Override
    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissionsCallback geolocationPermissionsCallback) {
        geolocationPermissionsCallback.invoke(origin, true, false);
        super.onGeolocationPermissionsShowPrompt(origin, geolocationPermissionsCallback);
    }

    /**
     * 获取网页标题
     */
    private String getWebTitle(WebView webView) {
        WebBackForwardList forwardList = webView.copyBackForwardList();
        WebHistoryItem currentItem = forwardList.getCurrentItem();
        return currentItem.getTitle();
    }
}
