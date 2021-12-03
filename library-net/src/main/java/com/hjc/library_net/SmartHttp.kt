package com.hjc.library_net

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.hjc.library_base.BaseApplication
import com.hjc.library_net.interceptor.LogInterceptor
import com.hjc.library_net.interceptor.OfflineCacheInterceptor
import com.hjc.library_net.interceptor.OnlineCacheInterceptor
import com.hjc.library_net.security.AllTrustManager
import com.hjc.library_net.security.SslContextFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.File
import java.util.concurrent.TimeUnit




/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Http封装
 */
object SmartHttp {

    /**
     * 初始化OkHttpClientBuilder
     */
    private val mOkHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    /**
     * 初始化RetrofitBuilder
     */
    private val mRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()

    /**
     * 默认超时时间
     */
    private const val DEFAULT_TIME_OUT: Long = 20L

    init {
        mOkHttpBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .retryOnConnectionFailure(true)
//            .authenticator(TokenAuthenticator())

        mRetrofitBuilder.client(mOkHttpBuilder.build())
    }

    /**
     * 设置服务器地址
     */
    fun setBaseUrl(baseUrl: String): SmartHttp {
        mRetrofitBuilder.baseUrl(baseUrl)
        return this
    }

    /**
     * 设置超时时间
     */
    fun setTimeout(timeout: Long): SmartHttp {
        mOkHttpBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
        return this
    }

    /**
     * 是否添加Log日志
     */
    fun setDebug(isDebug: Boolean): SmartHttp {
        if (isDebug) {
            mOkHttpBuilder.addInterceptor(LogInterceptor())
        }
        return this
    }

    /**
     * 添加拦截器
     */
    fun addInterceptor(interceptor: Interceptor): SmartHttp {
        mOkHttpBuilder.addInterceptor(interceptor)
        return this
    }

    /**
     * 添加转换器
     */
    fun addConverter(converterFactory: Converter.Factory): SmartHttp {
        mRetrofitBuilder.addConverterFactory(converterFactory)
        return this
    }

    /**
     * 添加CallAdapter
     */
    fun addCallAdapterFactory(factory: CallAdapter.Factory): SmartHttp {
        mRetrofitBuilder.addCallAdapterFactory(factory)
        return this
    }

    /**
     * 添加缓存
     */
    fun addCache(): SmartHttp {
        val cacheFile = File(BaseApplication.getApp().cacheDir, "OkhttpCache")
        val cacheSize = 10 * 1024 * 1024 // 10 MiB
        val cache = Cache(cacheFile, cacheSize.toLong())

        // addInterceptor()先于addNetworkInterceptor()执行
        mOkHttpBuilder
            .addInterceptor(OfflineCacheInterceptor())
            .addNetworkInterceptor(OnlineCacheInterceptor())
            .cache(cache)
        return this
    }

    /**
     * 添加证书
     */
    fun addSslSocketFactory(): SmartHttp {
        val sslSocketFactory = SslContextFactory.getSSLSocketFactory()
        if (sslSocketFactory != null) {
            mOkHttpBuilder.sslSocketFactory(sslSocketFactory, AllTrustManager())
        }
        return this
    }

    /**
     * 获取Retrofit对象
     */
    fun getRetrofit(): Retrofit {
        return mRetrofitBuilder
            .client(mOkHttpBuilder.build())
            .build()
    }

}
