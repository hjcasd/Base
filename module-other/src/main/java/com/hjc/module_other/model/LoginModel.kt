package com.hjc.module_other.model

import com.hjc.library_net.RetrofitClient
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.model.LoginBean

class LoginModel {

    suspend fun login(name: String?, password: String?): BaseResponse<LoginBean> {
        return RetrofitClient.getApiService1().login(name, password)
    }
}