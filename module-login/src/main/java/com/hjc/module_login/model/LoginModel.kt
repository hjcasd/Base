package com.hjc.module_login.model

import com.hjc.library_common.model.CommonModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.module_login.http.LoginApiService
import com.hjc.module_login.http.entity.LoginBean
import io.reactivex.Observable

class LoginModel : CommonModel() {

    private val mApi = getApiService(LoginApiService::class.java)

    fun login(name: String?, password: String?): Observable<BaseResponse<LoginBean>> {
        return mApi.login(name, password)
            .compose(RxSchedulers.ioToMain())
    }
}