package com.hjc.library_net.security;

import android.annotation.SuppressLint;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * @Author: HJC
 * @Date: 2021/9/13 15:28
 * @Description: 主机名校验(默认不校验)
 */
public class AllHostnameVerifier implements HostnameVerifier {

    @SuppressLint("BadHostnameVerifier")
    @Override
    public boolean verify(String hostname, SSLSession session) {
        //        校验hostname是否正确，如果正确则建立连接
        return true;
    }
}
