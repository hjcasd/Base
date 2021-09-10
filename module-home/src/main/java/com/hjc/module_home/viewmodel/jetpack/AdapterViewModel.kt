package com.hjc.module_home.viewmodel.jetpack

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.hjc.library_base.viewmodel.BaseViewModel

class AdapterViewModel(application: Application) : BaseViewModel(application) {

    // 一个 LiveData对象通常存储在ViewModel对象中，并通过getter方法访问
    val imageData = MutableLiveData<String>()

    // getter
    val imageRoundData = MutableLiveData<String>()

    // getter
    val imageCircleData = MutableLiveData<String>()

    fun loadImage() {
        imageData.value =
            "http://t9.baidu.com/it/u=2268908537,2815455140&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1591169778&t=8cb09b0b4b4cb365a882248f5bb61496"
    }

    fun loadRoundImage() {
        imageRoundData.value =
            "http://t8.baidu.com/it/u=1484500186,1503043093&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1591084878&t=5ae2ec70ab36fd943f2f99e2fc3099ad"
    }

    fun loadCircleImage() {
        imageCircleData.value =
            "http://t7.baidu.com/it/u=3616242789,1098670747&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1591169778&t=fa7890c7a1d63d1e2a2f725ef217ada5"
    }
}