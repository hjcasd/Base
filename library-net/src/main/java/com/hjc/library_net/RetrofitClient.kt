package com.hjc.library_net

import android.annotation.SuppressLint
import com.hjc.library_net.config.HttpConfig
import com.hjc.library_net.interceptor.BaseUrlInterceptor
import com.hjc.library_net.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:55
 * @Description: Retrofit封装
 */
object RetrofitClient {

    private lateinit var mRetrofit: Retrofit

    fun init(isDebug: Boolean) {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.connectTimeout(HttpConfig.HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(HttpConfig.HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(HttpConfig.HTTP_TIME_OUT.toLong(), TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .sslSocketFactory(createSSLSocketFactory(), mTrustAllCerts[0] as X509TrustManager)
            .hostnameVerifier(TrustAllHostnameVerifier())
            .addInterceptor(BaseUrlInterceptor())

        if (isDebug) {
            builder.addInterceptor(LogInterceptor())
        }

        mRetrofit = Retrofit.Builder()
            .baseUrl(HttpConfig.DEFAULT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
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
        sc.init(null, mTrustAllCerts, SecureRandom())
        return sc.socketFactory
    }

    private val mTrustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
        @SuppressLint("TrustAllX509TrustManager")
        override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        @SuppressLint("TrustAllX509TrustManager")
        override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        }

        override fun getAcceptedIssuers(): Array<X509Certificate> {
            return arrayOf()
        }
    })

    private class TrustAllHostnameVerifier : HostnameVerifier {
        @SuppressLint("BadHostnameVerifier")
        override fun verify(hostname: String, session: SSLSession): Boolean {
            return true
        }
    }

}
