package com.hjc.library_net

import com.hjc.library_net.interceptor.LogInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Http封装
 */
object SmartHttp {

    // 初始化OkHttpClientBuilder
    private val mOkHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    // 初始化RetrofitBuilder
    private val mRetrofitBuilder: Retrofit.Builder = Retrofit.Builder()

    // 默认超时时间
    private const val DEFAULT_TIME_OUT: Long = 20L

    init {
        mOkHttpBuilder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        mRetrofitBuilder.client(mOkHttpBuilder.build())
    }

    fun setDebug(isDebug: Boolean): SmartHttp {
        if (isDebug) {
            mOkHttpBuilder.addInterceptor(LogInterceptor())
        }
        return this
    }

    fun setBaseUrl(baseUrl: String): SmartHttp {
        mRetrofitBuilder.baseUrl(baseUrl)
        return this
    }

    fun setTimeout(timeout: Long): SmartHttp {
        mOkHttpBuilder.connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
        return this
    }

    fun addInterceptor(interceptor: Interceptor): SmartHttp {
        mOkHttpBuilder.addInterceptor(interceptor)
        return this
    }

    fun addConverter(converterFactory: Converter.Factory): SmartHttp {
        mRetrofitBuilder.addConverterFactory(converterFactory)
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
