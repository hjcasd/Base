package com.hjc.library_base.lifecycle

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.blankj.utilcode.util.LogUtils
import com.hjc.library_base.BaseApplication

/**
 * @Author: HJC
 * @Date: 2021/12/22 15:36
 * @Description: App前后台状态监听
 */
class ApplicationLifecycleObserver : LifecycleObserver {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_START)
    fun onAppForeground() {
        LogUtils.e("onAppForeground")
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    fun onAppBackground() {
        Toast.makeText(BaseApplication.getApp(), "应用切换到后台了哦", Toast.LENGTH_SHORT).show()
    }

}