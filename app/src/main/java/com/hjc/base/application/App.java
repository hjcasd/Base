package com.hjc.base.application;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.hjc.base.BuildConfig;
import com.hjc.base.utils.BuglyUtils;
import com.hjc.baselib.loadsir.EmptyCallback;
import com.hjc.baselib.loadsir.ErrorCallback;
import com.hjc.baselib.loadsir.LoadingCallback;
import com.hjc.baselib.loadsir.ShimmerCallback;
import com.hjc.baselib.loadsir.TimeoutCallback;
import com.hjc.baselib.utils.ActivityHelper;
import com.hjc.webviewlib.X5WebUtils;
import com.kingja.loadsir.core.LoadSir;


/**
 * @Author: HJC
 * @Date: 2019/1/4 14:46
 * @Description: application
 */
public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initUtils();
        initLoadSir();
        initARouter();
        BuglyUtils.init(this);
        X5WebUtils.init(this);

        registerActivity();
    }

    /**
     * 全局管理Activity
     */
    private void registerActivity() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                ActivityHelper.addActivity(activity);
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                ActivityHelper.removeActivity(activity.getClass());
            }
        });
    }


    /**
     * 初始化工具类
     */
    private void initUtils() {
        Utils.init(this);

        LogUtils.Config config = LogUtils.getConfig();
        config.setLogSwitch(BuildConfig.DEBUG);
        config.setGlobalTag("tag");
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new LoadingCallback())
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new ShimmerCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
