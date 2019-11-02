package com.hjc.base.utils;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.hjc.base.BuildConfig;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: HJC
 * @Date: 2019/11/2 15:40
 * @Description: 初始化Bugly工具类
 */
public class BuglyUtils {

    private BuglyUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void init(Context context) {
        if (context instanceof Application) {
            // 获取当前包名
            String packageName = context.getPackageName();
            // 获取当前进程名
            String processName = getProcessName(android.os.Process.myPid());
            // 设置是否为上报进程
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
            strategy.setUploadProcess(processName == null || processName.equals(packageName));
            CrashReport.initCrashReport(context, "04002332f3", BuildConfig.IS_DEBUG, strategy);
        } else {
            throw new UnsupportedOperationException("context must be application...");
        }
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }
}
