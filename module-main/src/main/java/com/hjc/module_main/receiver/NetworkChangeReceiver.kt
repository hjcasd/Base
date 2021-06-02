package com.hjc.module_main.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.blankj.utilcode.util.ToastUtils

/**
 * @Author: HJC
 * @Date: 2020/12/20 16:09
 * @Description: 网络变化BroadcastReceiver
 */
class NetworkChangeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        //系统服务类
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        //判断网络是否可用
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable) {
            ToastUtils.showShort("网络可用")
        } else {
            ToastUtils.showShort("网络不可用")
        }
    }
}