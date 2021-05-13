package com.hjc.base

import android.app.Activity
import android.os.Bundle
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.hjc.base.constant.AppConstants
import com.hjc.base.utils.BuglyUtils
import com.hjc.baselib.loadsir.*
import com.hjc.baselib.utils.ActivityHelper
import com.hjc.webviewlib.X5WebUtils
import com.kingja.loadsir.core.LoadSir

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:17
 * @Description: Application
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initUtils()
        initLoadSir()
        initARouter()
        BuglyUtils.init(this)
        X5WebUtils.init(this)
        registerActivity()
    }


    /**
     * 初始化工具类
     */
    private fun initUtils() {
        Utils.init(this)
        val config = LogUtils.getConfig()
        config.isLogSwitch = AppConstants.APP_IS_DEBUG
        config.globalTag = "tag"
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

    /**
     * 初始化路由
     */
    private fun initARouter() {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (AppConstants.APP_IS_DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(this)
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