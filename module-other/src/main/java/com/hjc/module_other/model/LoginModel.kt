package com.hjc.module_other.model

import com.hjc.library_common.model.CommonModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.module_other.http.entity.LoginBean
import com.hjc.module_other.http.OtherService

class LoginModel : CommonModel() {

    private val mApi = getApiService(OtherService::class.java)

    suspend fun login(name: String?, password: String?): BaseResponse<LoginBean> {
        return mApi.login(name, password)
    }
}