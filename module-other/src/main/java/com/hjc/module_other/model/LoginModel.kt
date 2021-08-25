package com.hjc.module_other.model

import com.hjc.library_common.model.CommonModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.module_other.http.OtherService
import com.hjc.module_other.http.entity.LoginBean
import io.reactivex.Observable

class LoginModel : CommonModel() {

    private val mApi = getApiService(OtherService::class.java)

    fun login(name: String?, password: String?): Observable<BaseResponse<LoginBean>> {
        return mApi.login(name, password)
            .compose(RxSchedulers.ioToMain())
    }
}