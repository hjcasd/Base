package com.hjc.base.http;


import com.hjc.base.bean.ArticleBean;
import com.hjc.base.bean.LoginReq;
import com.hjc.base.bean.LoginResp;
import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.config.URLConfig;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: Retrofit接口请求
 */
public interface Api {

    //登录
    @POST(URLConfig.URL_LOGIN)
    Observable<BaseResponse<LoginResp>> login(@Body LoginReq req);


    //列表文章
    @GET("article/list/{page}/json")
    Observable<ArticleBean> getArticleList(@Path("page") int page, @Query("cid") Integer cid);

    /**
     * 下载App
     *
     * @param url 下载apk链接
     * @return 下载响应数据
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String url);

}
