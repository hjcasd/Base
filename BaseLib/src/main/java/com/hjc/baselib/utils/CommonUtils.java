package com.hjc.baselib.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.blankj.utilcode.util.TimeUtils;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;


/**
 * @Author: HJC
 * @Date: 2019/7/1 17:52
 * @Description: 公共工具类
 */
public class CommonUtils {

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


    /**
     * MD5加密
     *
     * @param str 要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(str.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * 合并数组
     *
     * @param first  第一个数组
     * @param second 第二个数组
     * @param <T>    T类型
     * @return 新数组
     */
    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }


    /**
     * 把参数按照参数名称排序
     *
     * @param fields 参数
     */
    public static void sortField(Field[] fields) {
        Arrays.sort(fields, (o1, o2) -> {
            String key1 = o1.getName();
            String key2 = o2.getName();
            return key1.compareTo(key2);
        });
    }

    /**
     * 将date日期装换为yyyy-MM-dd格式的字符串
     *
     * @param date 日期
     * @return 指定格式字符串
     */
    public static String getTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return TimeUtils.date2String(date, sdf);
    }
}

