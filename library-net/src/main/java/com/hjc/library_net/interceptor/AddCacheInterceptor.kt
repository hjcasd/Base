package com.hjc.library_net.interceptor

import com.blankj.utilcode.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:15
 * @Description: 缓存拦截器
 */
class AddCacheInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetworkUtils.isConnected()) {
            //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
            //有网络时则根据缓存时长来决定是否发出请求
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE).build()
        }
        val response = chain.proceed(request)
        return if (NetworkUtils.isConnected()) {
            //有网络情况下，超过1分钟，则重新请求，否则直接使用缓存数据
            val maxAge = 60 //缓存一分钟
            val cacheControl = "public,max-age=$maxAge"
            //当然如果你想在有网络的情况下都直接走网络，那么只需要
            //将其超时时间maxAge设为0即可
            response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma").build()
        } else {
            //无网络时直接取缓存数据，该缓存数据保存1周
            val maxStale = 60 * 60 * 24 * 7 //1周
            val cacheControl = "public,only-if-cached,max-stale=$maxStale"
            response.newBuilder()
                .header("Cache-Control", cacheControl)
                .removeHeader("Pragma").build()
        }
    }
}