package com.hjc.library_net;

import com.hjc.library_net.interceptor.LogInterceptor;
import com.hjc.library_net.security.AllTrustManager;
import com.hjc.library_net.security.SslContextFactoryHelper;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Author: HJC
 * @Date: 2021/9/23 10:27
 * @Description: Http封装类
 */
public class SmartHttp {

    private static SmartHttp mSmartHttp;

    // 初始化OkHttpClientBuilder
    private final OkHttpClient.Builder mOkHttpBuilder = new OkHttpClient.Builder();

    // 初始化RetrofitBuilder
    private final Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder();

    // 默认超时时间
    private static final long DEFAULT_TIME_OUT = 20L;

    private SmartHttp() {
        mOkHttpBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        mRetrofitBuilder.client(mOkHttpBuilder.build());
    }

    public static SmartHttp getInstance() {
        if (mSmartHttp == null) {
            synchronized (SmartHttp.class) {
                if (mSmartHttp == null) {
                    mSmartHttp = new SmartHttp();
                }
            }
        }
        return mSmartHttp;
    }

    /**
     * 设置服务器地址
     */
    public SmartHttp setBaseUrl(String baseUrl) {
        mRetrofitBuilder.baseUrl(baseUrl);
        return this;
    }

    /**
     * 设置超时时间
     */
    public SmartHttp setTimeout(long timeout) {
        mOkHttpBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
                .readTimeout(timeout, TimeUnit.SECONDS)
                .writeTimeout(timeout, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 是否添加Log日志
     */
    public SmartHttp setDebug(boolean isDebug) {
        if (isDebug) {
            mOkHttpBuilder.addInterceptor(new LogInterceptor());
        }
        return this;
    }

    /**
     * 添加拦截器
     */
    public SmartHttp addInterceptor(Interceptor interceptor) {
        mOkHttpBuilder.addInterceptor(interceptor);
        return this;
    }

    /**
     * 添加转换器
     */
    public SmartHttp addConverter(Converter.Factory converterFactory) {
        mRetrofitBuilder.addConverterFactory(converterFactory);
        return this;
    }

    /**
     * 添加CallAdapter
     */
    public SmartHttp addCallAdapterFactory(CallAdapter.Factory factory) {
        mRetrofitBuilder.addCallAdapterFactory(factory);
        return this;
    }

    /**
     * 添加证书
     */
    public SmartHttp addSslSocketFactory() {
        SSLSocketFactory sslSocketFactory = SslContextFactoryHelper.getSSLSocketFactory();
        if (sslSocketFactory != null) {
            mOkHttpBuilder.sslSocketFactory(sslSocketFactory, new AllTrustManager());
        }
        return this;
    }

    /**
     * 获取Retrofit对象
     */
    public Retrofit getRetrofit() {
        return mRetrofitBuilder
                .client(mOkHttpBuilder.build())
                .build();
    }

}
