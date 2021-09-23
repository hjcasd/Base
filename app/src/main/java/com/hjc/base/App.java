package com.hjc.base;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.hjc.base.global.AppConstants;
import com.hjc.library_base.BaseApplication;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:46
 * @Description: application
 */
public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initUtils();
        initARouter();
    }

    /**
     * 初始化工具类
     */
    private void initUtils() {
        Utils.init(this);

        LogUtils.Config config = LogUtils.getConfig();
        config.setLogSwitch(AppConstants.APP_IS_DEBUG);
        config.setGlobalTag("tag");
    }

    /**
     * 初始化路由
     */
    private void initARouter() {
        if (AppConstants.APP_IS_DEBUG) {
            // 打印日志
            ARouter.openLog();
            // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

}
