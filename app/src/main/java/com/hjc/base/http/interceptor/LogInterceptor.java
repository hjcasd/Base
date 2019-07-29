package com.hjc.base.http.interceptor;

import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.utils.FormatUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:51
 * @Description: 日志拦截器
 */
public class LogInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);

        BufferedSource source = response.body().source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();

        String requestJson = "请求信息:"
                .concat("\nrequest code ====== " + response.code())
                .concat("\nrequest url ====== " + request.url())
                .concat("\nrequest duration ====== " + (response.receivedResponseAtMillis() - response.sentRequestAtMillis()) + "ms")
                .concat("\nrequest header ====== " + request.headers().toString())
                .concat("\nrequest body ====== " + bodyToString(request.body()));

        LogUtils.e(requestJson);
        String responseJson = buffer.clone().readString(UTF8);
        FormatUtils.formatJsonAndLog(responseJson);

        return response;
    }

    /**
     * 请求体转String
     *
     * @param request 请求体
     * @return String 类型的请求体
     */
    private static String bodyToString(final RequestBody request) {
        try {
            final Buffer buffer = new Buffer();
            request.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final Exception e) {
            return "no request body";
        }
    }
}
