package com.hjc.library_base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.hjc.library_base.loadsir.callback.DefaultEmptyCallback
import com.hjc.library_base.loadsir.callback.DefaultErrorCallback
import com.hjc.library_base.loadsir.callback.DefaultProgressCallback
import com.hjc.library_base.loadsir.callback.DefaultTimeoutCallback
import com.hjc.library_base.utils.ActivityHelper
import com.kingja.loadsir.core.LoadSir
import tech.oom.idealrecorder.IdealRecorder

/**
 * @Author: HJC
 * @Date: 2021/2/1 9:48
 * @Description: Application基类
 */
open class BaseApplication : MultiDexApplication() {

    companion object {

        /**
         * 应用实例
         */
        private lateinit var mInstance: Application

        /**
         * 获取应用实例
         */
        fun getApp(): Application {
            return mInstance
        }

    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this

        IdealRecorder.getInstance().init(this)
        initLoadSir()
        registerActivity()
    }

    /**
     * 初始化loadSir
     */
    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(DefaultProgressCallback())
            .addCallback(DefaultErrorCallback())
            .addCallback(DefaultEmptyCallback())
            .addCallback(DefaultTimeoutCallback())
            .commit()
    }

    /**
     * 全局Activity管理
     */
    private fun registerActivity() {
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                ActivityHelper.addActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityHelper.removeActivity(activity::class.java)
            }

        })
    }

}