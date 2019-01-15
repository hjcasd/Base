package com.hjc.base.http.exception;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:48
 * @Description: Code码说明类
 */
public class ServerCode {
    public static final String CODE_SUCCESS = "000";  //请求成功
    public static final String CODE_ID_ERROR = "003";  //身份证过期
    public static final String CODE_VERIFY_SMS = "005";  //需要短信验证码验证
    public static final String CODE_TOKEN_ERROR = "104";  //登录状态失效
}
