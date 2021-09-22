package com.hjc.library_net.service;

import com.hjc.library_net.bean.BaseResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CommonService {

    /**
     * 测试接口
     */
    @POST("/xxx/xxx/xxx")
    Observable<BaseResponse<Object>> test(@Body Object req);

}
