package com.hjc.module_other.utils

import android.graphics.Bitmap
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.ImageUtils
import com.blankj.utilcode.util.SPUtils
import com.hjc.library_base.BaseApplication
import java.io.File

/**
 * @Author: HJC
 * @Date: 2019/7/17 14:28
 * @Description: 安装apk工具类
 */
object ApkUtils {

    private const val DOWNLOAD_APK_DIR = "Base"

    private const val DOWNLOAD_APK_NAME = "base.apk"

    /**
     * 获取安装文件目录
     */
    fun getApkDir(): String {
        return BaseApplication.getApp().cacheDir.absolutePath + File.separator + DOWNLOAD_APK_DIR
    }

    /**
     * 获取安装文件目录
     */
    fun getApkPath(): String {
        return getApkDir() + File.separator + DOWNLOAD_APK_NAME
    }


    /**
     * 判断是否已经下载APK
     */
    fun appIsDownloaded(): Boolean {
        val file = File(getApkPath())
        if (file.exists()) {
            return SPUtils.getInstance().getBoolean("isDownloadedApk", false)
        }
        return false
    }

    /**
     * 获取App图标
     */
    fun getAppIcon(): Bitmap {
        val appIconDrawable = AppUtils.getAppIcon()
        return ImageUtils.drawable2Bitmap(appIconDrawable)
    }

    /**
     * 安装Apk
     */
    fun installApk() {
        val file = File(getApkPath())
        AppUtils.installApp(file)
    }

}