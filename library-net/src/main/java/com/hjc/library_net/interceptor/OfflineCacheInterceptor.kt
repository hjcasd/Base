package com.hjc.library_net.interceptor

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.NetworkUtils
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:15
 * @Description: 缓存拦截器(离线)
 */
class OfflineCacheInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        LogUtils.e("缓存拦截器---离线")

        var request = chain.request()
        if (!NetworkUtils.isConnected()) {
            //无网络下强制使用缓存，无论缓存是否过期,此时该请求实际上不会被发送出去。
            //有网络时则根据缓存时长来决定是否发出请求
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE).build()

            //无网络时直接取缓存数据，该缓存数据保存1周
            val maxStale = 60 * 60 * 24 * 7 //1周
            val cacheControl = "public,only-if-cached,max-stale=$maxStale"
            request.newBuilder()
                .header("Cache-Control", cacheControl)
                .build()
        }
        return chain.proceed(request)
    }
}