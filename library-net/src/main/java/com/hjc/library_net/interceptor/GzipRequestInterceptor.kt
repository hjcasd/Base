package com.hjc.library_net.interceptor

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.Response
import okio.BufferedSink
import okio.GzipSink
import okio.buffer
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2021/4/5 15:09
 * @Description: Gzip压缩
 */
class GzipRequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
            return chain.proceed(originalRequest)
        }
        val compressedRequest = originalRequest.newBuilder()
            .header("Content-Encoding", "gzip")
            .method(originalRequest.method(), gzipBody(originalRequest.body()))
            .build()
        return chain.proceed(compressedRequest)
    }

    /**
     * Gzip压缩Body
     */
    private fun gzipBody(body: RequestBody?): RequestBody {
        return object : RequestBody() {
            override fun contentType(): MediaType? {
                return body?.contentType()
            }

            override fun contentLength(): Long {
                // 无法提前知道压缩后的数据大小
                return -1
            }

            @Throws(IOException::class)
            override fun writeTo(sink: BufferedSink) {
                val gzipSink = GzipSink(sink).buffer()
                body?.writeTo(gzipSink)
                gzipSink.close()
            }
        }
    }
}