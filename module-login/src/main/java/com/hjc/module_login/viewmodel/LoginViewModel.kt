package com.hjc.module_login.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_common.utils.AccountHelper
import com.hjc.library_common.viewmodel.KotlinViewModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.observer.CommonObserver
import com.hjc.module_login.http.entity.LoginBean
import com.hjc.module_login.model.LoginModel

class LoginViewModel(application: Application) : KotlinViewModel(application) {

    private val loginModel = LoginModel()

    val loginData = MutableLiveData<Boolean>()
    val usernameData = MutableLiveData<String>()
    val passwordData = MutableLiveData<String>()

    fun login() {
        if (StringUtils.isEmpty(usernameData.value)) {
            ToastUtils.showShort("请输入用户名")
            return
        }
        if (StringUtils.isEmpty(passwordData.value)) {
            ToastUtils.showShort("请输入密码")
            return
        }

        loginModel.login(usernameData.value, passwordData.value)
            .subscribe(object : CommonObserver<LoginBean>(this, true) {
                override fun onSuccess(response: LoginBean?) {
                    ToastUtils.showShort("登录成功")
                    LogUtils.e("result: ${response?.username}")
                    AccountHelper.isLogin = true
                    loginData.value = true
                }

                override fun onFailure(e: Throwable, response: BaseResponse<LoginBean>?) {
                    super.onFailure(e, response)
                    loginData.value = false
                }

            })
    }
}