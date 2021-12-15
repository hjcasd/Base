package com.hjc.library_common

import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.facebook.stetho.Stetho
import com.hjc.library_base.BaseApplication
import com.hjc.library_common.global.AppConstants
import com.hjc.library_common.global.HttpConfig
import com.hjc.library_common.module.IModuleInit
import com.hjc.library_common.utils.BuglyUtils
import com.hjc.library_net.SmartHttp
import com.hjc.library_net.interceptor.BaseUrlInterceptor
import com.hjc.library_net.utils.GsonHelper
import com.hjc.library_web.utils.X5WebUtils
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: HJC
 * @Date: 2021/2/1 15:32
 * @Description: 通用库初始化操作
 */
class CommonModuleInit : IModuleInit {

    override fun initModuleApp(application: BaseApplication) {
        initUtils(application)
        initARouter(application)
        initBugly(application)
        X5WebUtils.init(application)
        initHttp()
        initStetho(application)
//        initRecord(application)
    }

    /**
     * 初始化工具类
     */
    private fun initUtils(application: BaseApplication) {
        Utils.init(application)
        val config = LogUtils.getConfig()
        config.isLogSwitch = AppConstants.APP_IS_DEBUG
        config.globalTag = "tag"
    }

    /**
     * 初始化路由
     */
    private fun initARouter(application: BaseApplication) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (AppConstants.APP_IS_DEBUG) {
            // 打印日志
            ARouter.openLog()
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug()
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(application)
    }

    /**
     * 初始化Bugly
     */
    private fun initBugly(application: BaseApplication) {
        BuglyUtils.init(application, AppConstants.BUGLY_CODE, AppConstants.APP_IS_DEBUG)
    }

    /**
     * 初始化Http配置
     */
    private fun initHttp() {
        SmartHttp.setBaseUrl(HttpConfig.BASE_URL)
            .setTimeout(HttpConfig.HTTP_TIME_OUT)
            .addInterceptor(BaseUrlInterceptor())
            .setDebug(AppConstants.APP_IS_DEBUG)
            .addConverter(GsonConverterFactory.create(GsonHelper.buildGson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .addCache()
    }

    /**
     * 初始化Stetho(用于监控网络请求等)
     * chrome://inspect/#devices
     */
    private fun initStetho(application: BaseApplication) {
        if (AppConstants.APP_IS_DEBUG) {
            Stetho.initializeWithDefaults(application)
        }
    }

}