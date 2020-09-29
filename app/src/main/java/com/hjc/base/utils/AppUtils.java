package com.hjc.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

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
}
