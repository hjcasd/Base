package com.hjc.base.http.bean;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:46
 * @Description: 通用返回Json封装
 */
public class BaseResponse<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseResponse{" + "code=" + code + ", message='" + message + '\'' + ", data=" + data + '}';
    }
}
