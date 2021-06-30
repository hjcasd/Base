package com.hjc.library_common.model

import com.hjc.library_base.model.BaseModel
import com.hjc.library_net.SmartHttp


/**
 * @Author: HJC
 * @Date: 2020/6/5 16:44
 * @Description: 通用Model
 */
open class CommonModel : BaseModel() {

    fun <T> getApiService(apiService: Class<T>): T {
        return SmartHttp.getRetrofit().create(apiService)
    }
}