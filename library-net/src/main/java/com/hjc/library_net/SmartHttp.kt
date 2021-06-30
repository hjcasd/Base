package com.hjc.library_net

import com.hjc.library_net.interceptor.BaseUrlInterceptor
import com.hjc.library_net.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Http封装
 */
object SmartHttp {

    private lateinit var mRetrofit: Retrofit

    private val builder: OkHttpClient.Builder = OkHttpClient.Builder()

    private var isDebug: Boolean
    private var baseUrl: String
    private var timeout: Long
    private var converterFactory: Converter.Factory

    init {
        this.isDebug = false
        this.baseUrl = ""
        this.timeout = 20L
        this.converterFactory = GsonConverterFactory.create()
    }

    fun setDebug(isDebug: Boolean): SmartHttp {
        this.isDebug = isDebug
        return this
    }

    fun setBaseUrl(baseUrl: String): SmartHttp {
        this.baseUrl = baseUrl
        return this
    }

    fun setTimeout(timeout: Long): SmartHttp {
        this.timeout = timeout

        return this
    }

    fun addConverter(factory: Converter.Factory): SmartHttp {
        this.converterFactory = factory
        return this
    }

    fun build() {
        builder.connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(BaseUrlInterceptor())
            .retryOnConnectionFailure(true)

        if (isDebug) {
            builder.addInterceptor(LogInterceptor())
        }

        this.mRetrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converterFactory)
            .client(builder.build())
            .build()
    }

    fun getRetrofit(): Retrofit {
        return mRetrofit
    }

    /**
     * 设置https证书
     */
    private fun createSSLSocketFactory(): SSLSocketFactory {
        val sc = SSLContext.getInstance("TLS")
        sc.init(null, arrayOf<TrustManager>(), SecureRandom())
        return sc.socketFactory
    }

}
