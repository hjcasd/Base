package com.hjc.module_other.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.blankj.utilcode.util.LogUtils

/**
 * @Author: HJC
 * @Date: 2020/12/20 15:37
 * @Description: 服务
 */
class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        LogUtils.e("onBind()....")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        LogUtils.e("onCreate()....")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        LogUtils.e("onStartCommand()....")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent): Boolean {
        LogUtils.e("onUnbind()....")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.e("onDestroy()....")
    }
}