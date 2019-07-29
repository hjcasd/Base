package com.hjc.base.http.interceptor;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: Token拦截器
 */
public class TokenInterceptor implements Interceptor {
    private static final String USER_TOKEN = "Authorization";
    private final String token;

    public TokenInterceptor(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (token == null || originalRequest.header(USER_TOKEN) != null) {
            return chain.proceed(originalRequest);
        }
        Request request = originalRequest.newBuilder()
                .header(USER_TOKEN, token)
                .build();
        return chain.proceed(request);
    }
}
