package com.hjc.library_base

import android.app.Activity
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.hjc.library_base.loadsir.*
import com.hjc.library_base.utils.ActivityHelper
import com.kingja.loadsir.core.LoadSir

/**
 * @Author: HJC
 * @Date: 2021/2/1 9:48
 * @Description: Application基类
 */
abstract class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initLoadSir()
        registerActivity()
    }

    private fun initLoadSir() {
        LoadSir.beginBuilder()
            .addCallback(ProgressCallback())
            .addCallback(ErrorCallback())
            .addCallback(EmptyCallback())
            .addCallback(TimeoutCallback())
            .addCallback(ShimmerCallback())
            .setDefaultCallback(ProgressCallback::class.java)
            .commit()
    }

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