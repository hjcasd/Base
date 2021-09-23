package com.hjc.library_net.interceptor

import com.blankj.utilcode.util.NetworkUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:15
 * @Description: 缓存拦截器(在线)
 */
class OnlineCacheInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        if (NetworkUtils.isConnected()) {
            //有网络情况下，超过1分钟，则重新请求，否则直接使用缓存数据
            val maxAge = 60 //缓存一分钟
            val cacheControl = "public,max-age=$maxAge"
            //如果想在有网络的情况下直接走网络请求，那么只需要将其超时时间maxAge设为0即可
            response = response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma").build()
        }
        return response
    }

}