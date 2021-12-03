package com.hjc.library_net.interceptor;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.library_net.exception.ServerCode;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * @Author: HJC
 * @Date: 2021/12/3 14:41
 * @Description: Token过期拦截器(HTTP Code为401才会调用该拦截器, 否则使用Interceptor拦截)
 */
public class TokenAuthenticator implements Authenticator {

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) {
        int code = response.code();
        if (code == ServerCode.CODE_TOKEN_INVALID) {
            ToastUtils.showShort("token过期了");
        }
        return response.request();
    }
}
