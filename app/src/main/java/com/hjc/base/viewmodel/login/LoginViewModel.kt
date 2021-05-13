package com.hjc.base.viewmodel.login

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.base.http.RetrofitClient
import com.hjc.base.model.LoginBean
import com.hjc.base.viewmodel.KotlinViewModel

class LoginViewModel(application: Application) : KotlinViewModel(application) {

    private val loginData = MutableLiveData<LoginBean>()
    private val usernameData = MutableLiveData<String>()
    private val passwordData = MutableLiveData<String>()

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
            RetrofitClient.getApiService1().login(usernameData.value, passwordData.value)
        }, { result ->
            loginData.value = result
        }, isShowLoading = true)
    }

    fun getLoginData(): MutableLiveData<LoginBean> {
        return loginData
    }

    fun getUsernameData(): MutableLiveData<String> {
        return usernameData
    }

    fun getPasswordData(): MutableLiveData<String> {
        return passwordData
    }
}