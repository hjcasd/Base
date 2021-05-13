package com.hjc.base.viewmodel.coroutines

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.base.http.RetrofitClient
import com.hjc.base.http.exception.ServerCode
import com.hjc.base.viewmodel.KotlinViewModel
import kotlinx.coroutines.*

class CoroutinesViewModel(application: Application) : KotlinViewModel(application) {

    fun loadData() {
        showLoading()
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    // 并发请求
                    val deferred1 = async {
                        RetrofitClient.getApiService2().getGankIoData("GanHuo", "All", 1, 20)
                    }
                    val deferred2 = async {
                        RetrofitClient.getApiService2().getHotFilm()
                    }
                    val result1 = deferred1.await()
                    val result2 = deferred2.await()
                    withContext(Dispatchers.Main) {
                        LogUtils.e("result1: $result1")
                        LogUtils.e("result2: $result2")
                    }
                }
            } catch (e: Throwable) {
                handleError(e)
            } finally {
                dismissLoading()
            }
        }
    }

    /**
     * launch: 创建一个协程,并在指定的线程上运行
     * 协程不会阻塞当前线程
     */
    fun launchTest() {
        GlobalScope.launch {
            // suspend关键字定义挂起函数
            val response = RetrofitClient.getApiService1().login("hjcasd", "asd123456789")
            if (response.errorCode == ServerCode.CODE_SUCCESS) {
                ToastUtils.showShort("请求成功")
            } else {
                ToastUtils.showShort(response.errorMsg)
            }
        }
    }


    /**
     * 1. async是有返回值的
     * 2. async的特点是不会阻塞当前线程，但会阻塞所在协程，也就是挂起
     * 3. 使用await()获取async闭包中返回的值
     */
    fun asyncTest() {
        GlobalScope.launch {
            val deferred = async {
                delay(1000L)
                return@async "哈哈哈"
            }
            val result = deferred.await()
            LogUtils.e("result: $result")
        }
    }

    /**
     * runBlocking: delay()可以阻塞当前的线程,类似于Thread.sleep()
     */
    fun runBlockingTest() {
        runBlocking {
            delay(1000L)
        }
        ToastUtils.showShort("哈哈哈")
    }

}