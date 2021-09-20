package com.hjc.library_net.interceptor

import com.blankj.utilcode.util.LogUtils
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 多BaseUrl拦截器
 */
class BaseUrlInterceptor : Interceptor {

    companion object {
        //多服务器地址1
        private const val TEST_BASE_URL1 = "https://gank.io"

        //多服务器地址2
        private const val TEST_BASE_URL2 = "https://api-m.mtime.cn"

        //多服务器地址2
        private const val TEST_BASE_URL3 = "http://10.67.200.215:8888"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val builder: Request.Builder = request.newBuilder()
        val oldHttpUrl: HttpUrl = request.url()
        //从request中获取headers，通过给定的键url_name
        val headerValues: List<String> = request.headers("url_name")
        if (headerValues.isNotEmpty()) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和OKHttp之间使用
            builder.removeHeader("url_name")
            //匹配获得新的BaseUrl
            val headerValue = headerValues[0]
            val newBaseUrl = getNewBaseUrl(headerValue, oldHttpUrl)
            //重建新的HttpUrl，修改需要修改的url部分
            return newBaseUrl?.let {
                val newFullUrl = oldHttpUrl
                    .newBuilder()
                    .scheme(it.scheme()) //更换网络协议
                    .host(it.host()) //更换主机名
                    .port(it.port()) //更换端口
                    .build()
                LogUtils.e("newFullUrl: $newFullUrl")
                chain.proceed(builder.url(newFullUrl).build())
            } ?: chain.proceed(request)
        }
        return chain.proceed(request)
    }

    private fun getNewBaseUrl(headerValue: String, oldHttpUrl: HttpUrl): HttpUrl? {
        return when (headerValue) {
            "test1" -> HttpUrl.parse(TEST_BASE_URL1)
            "test2" -> HttpUrl.parse(TEST_BASE_URL2)
            "test3" -> HttpUrl.parse(TEST_BASE_URL3)
            else -> oldHttpUrl
        }
    }
}
