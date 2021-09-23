package com.hjc.library_net.service

import com.hjc.library_net.bean.BaseResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * @Author: HJC
 * @Date: 2021/9/23 19:59
 * @Description: 通用接口服务
 */
interface CommonService {

    /**
     * 测试接口
     */
    @POST("/xxx/xxx/xxx")
    fun test(@Body req: Any?): Observable<BaseResponse<Any?>>

}