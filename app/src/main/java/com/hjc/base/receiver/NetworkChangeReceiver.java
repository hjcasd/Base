package com.hjc.base.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.blankj.utilcode.util.ToastUtils;

/**
 * @Author: HJC
 * @Date: 2020/12/20 16:09
 * @Description: 网络变化BroadcastReceiver
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //系统服务类
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        //判断网络是否可用
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            ToastUtils.showShort("网络可用");
        } else {
            ToastUtils.showShort("网络不可用");
        }
    }
}
