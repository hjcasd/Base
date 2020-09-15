package com.hjc.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: HJC
 * @Date: 2019/1/28 15:33
 * @Description: 常用方法封装
 */
public class AppUtils {

    /**
     * 实现文本复制功能
     *
     * @param context 上下文
     * @param text    复制的文本
     */
    public static void copy(Context context, String text) {
        if (!TextUtils.isEmpty(text)) {
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", text);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);
        }
    }

    /**
     * 使用浏览器打开链接
     *
     * @param context 上下文
     * @param link    链接地址
     */
    public static void openLink(Context context, String link) {
        Uri issuesUrl = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, issuesUrl);
        context.startActivity(intent);
    }

    /**
     * 分享
     *
     * @param context   上下文
     * @param extraText 内容
     */
    public static void share(Context context, String extraText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
        intent.putExtra(Intent.EXTRA_TEXT, extraText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, "分享"));
    }


    public static String getTranslateTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.CHINA);
        // 在主页面中设置当天时间
        Date nowTime = new Date();
        String currDate = sdf.format(nowTime);
        long currentMilliseconds = nowTime.getTime();// 当前日期的毫秒值
        long timeMilliseconds;
        Date date;
        try {
            // 将给定的字符串中的日期提取出来
            date = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
            return time;
        }
        if (date != null) {
            timeMilliseconds = date.getTime();
            long timeDifferent = currentMilliseconds - timeMilliseconds;

            if (timeDifferent < 60000) {// 一分钟之内
                return "刚刚";
            }
            if (timeDifferent < 3600000) {// 一小时之内
                long longMinute = timeDifferent / 60000;
                int minute = (int) (longMinute % 100);
                return minute + "分钟之前";
            }
            long l = 24 * 60 * 60 * 1000; // 每天的毫秒数
            if (timeDifferent < l) {// 小于一天
                long longHour = timeDifferent / 3600000;
                int hour = (int) (longHour % 100);
                return hour + "小时之前";
            }
            String currYear = currDate.substring(0, 4);
            String year = time.substring(0, 4);
            if (!year.equals(currYear)) {
                return time.substring(0, 10);
            }
            return time.substring(5, 10);
        }
        return time;
    }
}
