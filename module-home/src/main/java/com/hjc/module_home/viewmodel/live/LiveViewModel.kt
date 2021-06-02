package com.hjc.module_home.viewmodel.live

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.module_home.entity.UserBean
import com.hjc.module_home.model.LiveModel

class LiveViewModel(application: Application) : BaseViewModel(application) {

    private val liveModel: LiveModel = LiveModel()

    val userLiveData: MutableLiveData<UserBean> = MutableLiveData<UserBean>()

    fun showData() {
        val bean: UserBean = liveModel.user
        userLiveData.value = bean
    }

    fun changeData() {
        val bean = UserBean("李四", 28, true)
        userLiveData.value = bean
    }

    fun hideData() {
        val bean: UserBean = liveModel.user
        bean.isShow = false
        userLiveData.value = bean
    }

}