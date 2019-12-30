package com.hjc.base.application;

import androidx.multidex.MultiDexApplication;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.hjc.base.BuildConfig;
import com.hjc.base.utils.BuglyUtils;
import com.hjc.webviewlib.X5WebUtils;

import es.dmoral.toasty.Toasty;


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
        initARouter();
        initToasty();
        BuglyUtils.init(this);
        X5WebUtils.init(this);
    }


    /**
     * 初始化工具类
     */
    private void initUtils() {
        Utils.init(this);

        LogUtils.Config config = LogUtils.getConfig();
        config.setLogSwitch(BuildConfig.IS_DEBUG);
        config.setGlobalTag("tag");
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (BuildConfig.IS_DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    /**
     * 初始化Toasty
     */
    private void initToasty() {
        Toasty.Config.getInstance()
                .setTextSize(14)
                .apply();
    }
}
