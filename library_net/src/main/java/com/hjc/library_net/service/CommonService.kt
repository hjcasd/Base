package com.hjc.library_net.service

import com.hjc.library_net.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

interface CommonService {

    //登录
    @POST("/xxx/xxx/xxx")
    fun test(@Body req: Any?): Observable<BaseResponse<Any?>>

}