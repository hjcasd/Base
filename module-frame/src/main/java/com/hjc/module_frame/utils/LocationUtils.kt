package com.hjc.module_frame.utils

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import com.blankj.utilcode.util.Utils

/**
 * @Author: HJC
 * @Date: 2021/1/28 14:44
 * @Description: 定位相关工具类
 */
object LocationUtils {

    /**
     * 判断Gps是否可用
     *
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isGpsEnabled(): Boolean {
        val lm = Utils.getApp().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    /**
     * 判断定位是否可用
     *
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isLocationEnabled(): Boolean {
        val lm = Utils.getApp().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return (lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
    }


    /**
     * 打开Gps设置界面
     */
    fun openGpsSettings() {
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
        Utils.getApp().startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }

}