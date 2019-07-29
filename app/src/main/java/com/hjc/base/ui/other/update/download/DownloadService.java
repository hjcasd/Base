package com.hjc.base.ui.other.update.download;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.utils.ApkUtils;

import java.io.File;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:17
 * @Description: 下载服务类
 */
public class DownloadService extends IntentService {
    private static final int NOTIFY_ID = 0;
    private static final String CHANNEL_ID = "app_update_id";
    private static final CharSequence CHANNEL_NAME = "app_update_channel";

    private NotificationManager mNotificationManager;
    private NotificationCompat.Builder mBuilder;

    private int oldRate = 0;
    private String apkUrl;

    public DownloadService() {
        super("DownloadService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(android.content.Context.NOTIFICATION_SERVICE);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        apkUrl = intent.getStringExtra("apkUrl");
        setUpNotification();
        download();
    }

    private void setUpNotification() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(false);
            channel.enableLights(false);

            mNotificationManager.createNotificationChannel(channel);
        }

        mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);
        mBuilder.setContentTitle("开始下载")
                .setContentText("正在连接服务器")
                .setSmallIcon(R.mipmap.icon_update)
                .setLargeIcon(ApkUtils.getAppIcon(DownloadService.this))
                .setOngoing(true)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis());
        mNotificationManager.notify(NOTIFY_ID, mBuilder.build());
    }

    /**
     * 下载
     *
     */
    private void download() {
        DownloadManager.getInstance().download(apkUrl, new BaseDownloadObserver<File>() {
            @Override
            public void onDownloadSuccess(File file) {
                SPUtils.getInstance().put("isDownloadedApk", true);

                mNotificationManager.cancel(NOTIFY_ID);
                ApkUtils.installApp(DownloadService.this, file);
                //下载完自杀
                stopSelf();
            }

            @Override
            public void onDownloadFail(String msg) {
                ToastUtils.showShort("更新新版本出错，" + msg);

                mNotificationManager.cancel(NOTIFY_ID);
                stopSelf();
            }

            @Override
            public void onProgress(int progress, long total) {
                int rate = progress;
                if (oldRate != rate) {
                    if (mBuilder != null) {
                        mBuilder.setContentTitle("正在下载：" + AppUtils.getAppName())
                                .setContentText(progress + "%")
                                .setProgress(100, progress, false)
                                .setWhen(System.currentTimeMillis());
                        Notification notification = mBuilder.build();
                        notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONLY_ALERT_ONCE;
                        mNotificationManager.notify(NOTIFY_ID, notification);
                    }
                    //重新赋值
                    oldRate = rate;
                }
            }
        });
    }
}
