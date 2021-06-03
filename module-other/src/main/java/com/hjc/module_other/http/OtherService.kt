package com.hjc.module_other.http

import com.hjc.library_net.bean.BaseResponse
import com.hjc.module_other.entity.LoginBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: Other模块接口请求
 */
interface OtherService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") username: String?,
        @Field("password") pwd: String?
    ): BaseResponse<LoginBean>
}
