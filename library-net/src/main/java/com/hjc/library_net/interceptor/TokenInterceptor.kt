package com.hjc.library_net.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: Token拦截器
 */
class TokenInterceptor : Interceptor {

    companion object {

        /**
         * Token key
         */
        private const val TOKEN_KEY = "Authorization"

        /**
         * Token value
         */
        private const val TOKEN_VALUE = "xxx"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (originalRequest.header(TOKEN_KEY) != null) {
            return chain.proceed(originalRequest)
        }
        val request = originalRequest.newBuilder()
            .header(TOKEN_KEY, TOKEN_VALUE)
            .build()
        return chain.proceed(request)
    }

}
