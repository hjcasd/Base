package com.hjc.module_login.http

import com.hjc.library_net.bean.BaseResponse
import com.hjc.module_login.http.entity.LoginBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:53
 * @Description: Login模块接口Api
 */
interface LoginApiService {

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(
        @Field("username") username: String?,
        @Field("password") pwd: String?
    ): Observable<BaseResponse<LoginBean>>

}
