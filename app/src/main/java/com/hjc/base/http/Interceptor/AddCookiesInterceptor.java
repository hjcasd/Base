package com.hjc.base.http.Interceptor;



import android.support.annotation.NonNull;

import com.blankj.utilcode.util.StringUtils;
import com.hjc.base.utils.helper.AccountManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author: HJC
 * @Date: 2019/3/11 17:40
 * @Description: 将登录后的cookie添加到请求中
 */
public class AddCookiesInterceptor implements Interceptor {
    private static final String COOKIE = "Cookie";

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        String cookie = AccountManager.getInstance().getCookie();
        if (!StringUtils.isEmpty(cookie)){
            builder.addHeader(COOKIE, cookie);
        }
        return chain.proceed(builder.build());
    }
}
