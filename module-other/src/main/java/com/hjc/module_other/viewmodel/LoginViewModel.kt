package com.hjc.module_other.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_common.viewmodel.KotlinViewModel
import com.hjc.module_other.http.entity.LoginBean
import com.hjc.module_other.model.LoginModel

class LoginViewModel(application: Application) : KotlinViewModel(application) {

    private val loginModel = LoginModel()

    val loginData = MutableLiveData<LoginBean>()
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
        launchWrapper({
            loginModel.login(usernameData.value, passwordData.value)
        }, { result ->
            loginData.value = result
        }, isShowLoading = true)
    }
}