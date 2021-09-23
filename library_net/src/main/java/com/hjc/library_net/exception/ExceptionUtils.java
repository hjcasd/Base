package com.hjc.library_net.exception;

import org.json.JSONException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:48
 * @Description: 对返回的错误进行处理
 */
public class ExceptionUtils {

    private ExceptionUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String handleException(Throwable e) {
        String error = "未知错误";
        if (e instanceof UnknownHostException) {
            error = "网络不可用";
        } else if (e instanceof SocketTimeoutException) {
            error = "请求网络超时";
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            error = convertHttpCode(httpException);
        } else if (e instanceof ParseException || e instanceof JSONException) {
            error = "数据解析错误";
        }else if (e instanceof ApiException){
            ApiException apiException = (ApiException) e;
            error = convertServerCode(apiException);
        }
        return error;
    }

    private static String convertHttpCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }

    private static String convertServerCode(ApiException apiException){
        String msg = "未知错误";
        switch (apiException.getCode()) {
            case ServerCode.CODE_FAIL:
                msg = apiException.getMessage();
                break;

            case ServerCode.CODE_UN_LOGIN:
                msg = "登录失效,请重新登录";
                break;

            default:
                break;
        }
        return msg;
    }
}
