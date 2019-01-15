package com.hjc.base.http;

import com.hjc.base.http.Interceptor.LogInterceptor;
import com.hjc.base.http.config.HttpConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: OkHttp封装
 */
public class HttpClient {
    private static HttpClient mHttpClient;
    private OkHttpClient.Builder mBuilder;

    private HttpClient() {
        mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(HttpConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
//                .addNetworkInterceptor(new TokenInterceptor(null))  //添加Token拦截器
                .addInterceptor(new LogInterceptor());
    }

    //双重检验锁单例模式
    public static HttpClient getInstance() {
        if (mHttpClient == null) {
            synchronized (HttpClient.class) {
                if (mHttpClient == null) {
                    mHttpClient = new HttpClient();
                }
            }
        }
        return mHttpClient;
    }

    public OkHttpClient.Builder getBuilder() {
        return mBuilder;
    }
}
