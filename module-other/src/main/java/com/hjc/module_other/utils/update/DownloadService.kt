package com.hjc.module_other.utils.update

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.SPUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.module_other.R
import com.hjc.module_other.utils.ApkUtils
import java.io.File

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:17
 * @Description: 下载服务类
 */
@Suppress("DEPRECATION")
class DownloadService : IntentService("DownloadService") {

    private var mNotificationManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null

    private var apkUrl: String? = null
    private var oldRate = 0

    companion object {
        private const val NOTIFY_ID = 0
        private const val CHANNEL_ID = "app_update_id"
        private const val CHANNEL_NAME = "app_update_channel"
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onHandleIntent(intent: Intent?) {
        apkUrl = intent?.getStringExtra("apkUrl")
        setUpNotification()
        downloadApk()
    }

    private fun setUpNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(false)
            channel.enableLights(false)
            mNotificationManager?.createNotificationChannel(channel)
        }



        mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
        mBuilder?.setContentTitle("开始下载")
            ?.setContentText("正在连接服务器")
            ?.setSmallIcon(R.mipmap.other_icon_update)
            ?.setLargeIcon(ApkUtils.getAppIcon())
            ?.setOngoing(true)
            ?.setAutoCancel(true)
            ?.setWhen(System.currentTimeMillis())

        mNotificationManager?.notify(NOTIFY_ID, mBuilder?.build())
    }

    /**
     * 下载Apk文件
     */
    private fun downloadApk() {
        DownloadManager.download(apkUrl, object : BaseDownloadObserver<File>() {

            override fun onDownloadSuccess(t: File) {
                SPUtils.getInstance().put("isDownloadedApk", true)
                mNotificationManager?.cancel(NOTIFY_ID)
                ApkUtils.installApk()
                //下载完自杀
                stopSelf()
            }

            override fun onDownloadFail(msg: String?) {
                ToastUtils.showShort("更新新版本出错，$msg")
                mNotificationManager?.cancel(NOTIFY_ID)
                stopSelf()
            }

            override fun onProgress(progress: Int, total: Long) {
                if (oldRate != progress) {
                    mBuilder?.setContentTitle("正在下载：" + AppUtils.getAppName())
                        ?.setContentText("$progress%")
                        ?.setProgress(100, progress, false)
                        ?.setWhen(System.currentTimeMillis())

                    val notification: Notification? = mBuilder?.build()
                    notification?.flags =
                        Notification.FLAG_AUTO_CANCEL or Notification.FLAG_ONLY_ALERT_ONCE
                    mNotificationManager?.notify(NOTIFY_ID, notification)
                    //重新赋值
                    oldRate = progress
                }
            }
        })
    }

}