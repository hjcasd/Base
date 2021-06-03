package com.hjc.module_home.viewmodel.coroutines

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_common.viewmodel.KotlinViewModel
import com.hjc.module_home.model.CoroutinesModel
import kotlinx.coroutines.*

class CoroutinesViewModel(application: Application) : KotlinViewModel(application) {

    private val mModel = CoroutinesModel()

    fun loadData() {
        showLoading()
        viewModelScope.launch(Dispatchers.Main) {
            try {
                withContext(Dispatchers.IO) {
                    // 并发请求
                    val deferred1 = async {
                        mModel.getGankIoData()
                    }
                    val deferred2 = async {
                        mModel.getHotFilm()
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
            val response =  mModel.getGankIoData()
            if (response != null) {
                ToastUtils.showShort("请求成功")
            } else {
                ToastUtils.showShort("请求失败")
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