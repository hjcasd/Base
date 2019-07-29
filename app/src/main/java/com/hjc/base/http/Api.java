package com.hjc.base.http;


import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.config.URLConfig;
import com.hjc.base.model.request.LoginReq;
import com.hjc.base.model.request.UpdateReq;
import com.hjc.base.model.response.LoginResp;
import com.hjc.base.model.response.VersionResp;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: Retrofit接口请求
 */
public interface Api {
    /**
     * 检查版本更新
     *
     * @param req 更新请求bean
     * @return 更新响应bean
     */
    @POST(URLConfig.URL_CHECK_VERSION)
    Observable<BaseResponse<VersionResp>> checkVersion(@Body UpdateReq req);

    /**
     * 下载App
     *
     * @param url 下载apk链接
     * @return 下载响应数据
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);

    /**
     * 登录
     *
     * @param req 登录请求bean
     * @return 登录响应bean
     */
    @POST(URLConfig.URL_LOGIN)
    Observable<BaseResponse<LoginResp>> login(@Body LoginReq req);
}
