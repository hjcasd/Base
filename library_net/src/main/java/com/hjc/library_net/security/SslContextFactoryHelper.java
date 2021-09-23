package com.hjc.library_net.security;

import java.security.SecureRandom;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

/**
 * @Author: HJC
 * @Date: 2021/9/13 15:31
 * @Description: 证书工具类
 */
public class SslContextFactoryHelper {

    private SslContextFactoryHelper() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 获取证书
     */
    public static SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new AllTrustManager[0], new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
