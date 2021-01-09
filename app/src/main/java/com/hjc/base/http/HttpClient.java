package com.hjc.base.http;

import android.annotation.SuppressLint;

import com.hjc.base.BuildConfig;
import com.hjc.base.http.config.HttpConfig;
import com.hjc.base.http.interceptor.LogInterceptor;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: OkHttp封装
 */
public class HttpClient {
    private static HttpClient mHttpClient;
    private final OkHttpClient.Builder mBuilder;

    private HttpClient() {
        mBuilder = new OkHttpClient.Builder();
        mBuilder.connectTimeout(HttpConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(createSSLSocketFactory(), (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier(new TrustAllHostnameVerifier());

        if (BuildConfig.DEBUG) {
            mBuilder.addInterceptor(new LogInterceptor());
        }
    }

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

    OkHttpClient.Builder getBuilder() {
        return mBuilder;
    }

    /**
     * 设置https证书
     */
    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory sSLSocketFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new SecureRandom());
            sSLSocketFactory = sc.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sSLSocketFactory;
    }

    private final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }};

    private static class TrustAllHostnameVerifier implements HostnameVerifier {
        @SuppressLint("BadHostnameVerifier")
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
