package com.hjc.base.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.ui.WebActivity;

/**
 * @Author: HJC
 * @Date: 2019/1/28 15:54
 * @Description: 跳转逻辑封装类
 */
public class SchemeUtils {

    /**
     * @param context 上下文
     * @param url     页面地址
     * @param title   标题文字
     */
    public static void jumpToWeb(Context context, String url, String title) {
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!");
            return;
        }
        if (StringUtils.isEmpty(url)) {
            ToastUtils.showShort("链接地址不能为空");
            return;
        }
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("url", url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
