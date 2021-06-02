package com.hjc.library_net

import com.hjc.library_net.config.HttpConfig
import com.hjc.library_net.interceptor.BaseUrlInterceptor
import com.hjc.library_net.interceptor.LogInterceptor
import com.hjc.library_net.service.ApiService1
import com.hjc.library_net.service.ApiService2
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Retrofit封装
 */
object RetrofitClient {
    private var mRetrofit: Retrofit

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(HttpConfig.DEFAULT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(getBuilder().build())

            .build()
    }

    fun getApiService1(): ApiService1 {
        return mRetrofit.create(ApiService1::class.java)
    }

    fun getApiService2(): ApiService2 {
        return mRetrofit.create(ApiService2::class.java)
    }

    fun <T> getApiService(apiService: Class<T>): T{
        return mRetrofit.create(apiService)
    }

    private fun getBuilder(): OkHttpClient.Builder {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.connectTimeout(HttpConfig.HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(HttpConfig.HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(BaseUrlInterceptor())

        if (HttpConfig.IS_DEBUG) {
            builder.addInterceptor(LogInterceptor())
        }
        return builder
    }

}
