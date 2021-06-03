package com.hjc.module_home.http

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: 首页模块接口请求
 */
interface HomeService {

    @Headers("url_name:test1")
    @GET("/api/v2/data/category/{category}/type/{type}/page/{page}/count/{count}")
    suspend fun getGankIoData(
        @Path("category") category: String?,
        @Path("type") type: String?,
        @Path("page") page: Int,
        @Path("count") count: Int
    ): Any?

    /**
     * 热映榜电影
     */
    @Headers("url_name:test2")
    @GET("/Showtime/LocationMovies.api?locationId=561")
    suspend fun getHotFilm(): Any?
}
