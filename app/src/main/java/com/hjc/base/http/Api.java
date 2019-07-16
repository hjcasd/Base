package com.hjc.base.http;


import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.config.URLConfig;
import com.hjc.base.model.request.LoginReq;
import com.hjc.base.model.request.UpdateRequest;
import com.hjc.base.model.response.LoginResp;
import com.hjc.base.model.response.VersionBean;

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
    //检查版本更新
    @POST(URLConfig.URL_CHECK_VERSION)
    Observable<BaseResponse<VersionBean>> checkVersion(@Body UpdateRequest req);

    //下载App
    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);

    //登录
    @POST(URLConfig.URL_LOGIN)
    Observable<BaseResponse<LoginResp>> login(@Body LoginReq req);
}
