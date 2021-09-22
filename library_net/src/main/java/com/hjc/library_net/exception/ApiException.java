package com.hjc.library_net.exception;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:47
 * @Description: 自定义异常类(处理服务器code码异常)
 */
public class ApiException extends Exception {

    private final String code;

    public ApiException(String msg, String code) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}