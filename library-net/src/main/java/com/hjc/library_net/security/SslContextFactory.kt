package com.hjc.library_net.security

import com.blankj.utilcode.util.LogUtils
import java.security.SecureRandom
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager

/**
 * @Author: HJC
 * @Date: 2021/9/13 15:31
 * @Description: 证书工具类
 */
object SslContextFactory {

    /**
     * 获取证书
     */
    fun getSSLSocketFactory(): SSLSocketFactory? {
        try {
            val sc = SSLContext.getInstance("TLS")
            sc.init(null, arrayOf<TrustManager>(AllTrustManager()), SecureRandom())
            return sc.socketFactory
        } catch (e: Exception) {
            LogUtils.e("SslContextFactory: $e")
        }
        return null
    }

}
