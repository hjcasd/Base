package com.hjc.base.constant;

import android.os.Environment;

import java.io.File;

/**
 * @Author: HJC
 * @Date: 2019/1/4 15:05
 * @Description: 常量类
 */
public class AppConstants {
    public static final String DOWNLOAD_APK_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "YBT";
    public static final String DOWNLOAD_APK_NAME = "test.apk";
    public static final String DOWNLOAD_APK_PATH = DOWNLOAD_APK_DIR + File.separator + DOWNLOAD_APK_NAME;

    public static boolean isDebug = true;
}
