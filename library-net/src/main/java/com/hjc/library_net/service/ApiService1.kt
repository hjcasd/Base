package com.hjc.library_net.service

import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.bean.PageResponse
import com.hjc.library_net.model.ArticleBean
import com.hjc.library_net.model.LoginBean
import retrofit2.http.*

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: Retrofit接口请求1
 */
interface ApiService1 {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String?,
        @Field("password") pwd: String?
    ): BaseResponse<LoginBean>


    /**
     * 首页列表
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): BaseResponse<PageResponse<MutableList<ArticleBean>>>
}
