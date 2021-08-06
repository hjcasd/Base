package com.hjc.module_other.http

import com.hjc.library_net.bean.BaseResponse
import com.hjc.module_other.http.entity.LoginBean
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

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

    /**
     * 下载App
     *
     * @param url 下载apk链接
     * @return 下载响应数据
     */
    @Streaming
    @GET
    fun downloadApk(@Url url: String?): Observable<ResponseBody>


    /**
     * 上传录音文件
     */
    @Headers("url_name:test3")
    @Multipart
    @POST("/customize/voice/stream")
    suspend fun uploadVoiceFile(@Part body: MultipartBody.Part): Any?
}
