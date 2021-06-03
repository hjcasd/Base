package com.hjc.module_frame.http

import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.bean.PageResponse
import com.hjc.module_frame.entity.ArticleBean
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: Frame模块接口请求
 */
interface FrameService {

    /**
     * 列表
     */
    @GET("article/list/{page}/json")
    suspend fun getArticleList(@Path("page") page: Int): BaseResponse<PageResponse<MutableList<ArticleBean>>>
}
