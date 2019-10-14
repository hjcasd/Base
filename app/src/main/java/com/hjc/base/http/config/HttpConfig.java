package com.hjc.base.http.config;

import com.hjc.base.BuildConfig;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:40
 * @Description: 网络基本配置
 */
public class HttpConfig {
    /**
     * 超时时间(s)
     */
    public static int HTTP_TIME_OUT = 20;

    /**
     * 服务器地址
     */
    public static String BASE_URL = BuildConfig.BASE_URL;
}
