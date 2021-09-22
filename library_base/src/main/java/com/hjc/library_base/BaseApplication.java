package com.hjc.library_base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

import com.hjc.library_base.loadsir.callback.DefaultEmptyCallback;
import com.hjc.library_base.loadsir.callback.DefaultErrorCallback;
import com.hjc.library_base.loadsir.callback.DefaultProgressCallback;
import com.hjc.library_base.loadsir.callback.DefaultTimeoutCallback;
import com.hjc.library_base.utils.ActivityHelper;
import com.kingja.loadsir.core.LoadSir;

/**
 * @Author: HJC
 * @Date: 2021/2/1 9:48
 * @Description: Application基类
 */
public class BaseApplication extends MultiDexApplication {

    private static Application mInstance;

    public static Application getApp() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        initLoadSir();
        registerActivity();
    }

    /**
     * 初始化LoadSir
     */
    private void initLoadSir() {
        LoadSir.beginBuilder()
                .addCallback(new DefaultProgressCallback())
                .addCallback(new DefaultErrorCallback())
                .addCallback(new DefaultEmptyCallback())
                .addCallback(new DefaultTimeoutCallback())
                .commit();
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

}
