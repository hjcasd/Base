package com.hjc.base.utils.permission;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.yanzhenjie.permission.AndPermission;

/**
 * @Author: HJC
 * @Date: 2019/2/21 10:54
 * @Description: 权限管理封装类
 */
public class PermissionManager {
    private static PermissionManager mInstance;

    private Context mContext;
    private Activity mActivity;
    private Fragment mFragment;

    private PermissionManager() {
    }

    public static PermissionManager getInstance() {
        if (mInstance == null) {
            synchronized (PermissionManager.class) {
                if (mInstance == null) {
                    mInstance = new PermissionManager();
                }
            }
        }
        return mInstance;
    }

    public PermissionManager with(Context context) {
        this.mContext = context;
        return mInstance;
    }

    public PermissionManager with(Activity activity) {
        this.mActivity = activity;
        return mInstance;
    }

    public PermissionManager with(Fragment fragment) {
        this.mFragment = fragment;
        return mInstance;
    }

    /**
     * 申请权限(Context)
     *
     * @param callBack    回调
     * @param permissions 要申请的权限
     */
    public void requestPermission(PermissionCallBack callBack, String... permissions) {
        if (mContext == null) {
            return;
        }
        AndPermission.with(mContext)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(data -> callBack.onGranted())
                .onDenied(data -> callBack.onDenied())
                .start();
    }

    /**
     * 申请权限(Activity)
     *
     * @param callBack    回调
     * @param permissions 要申请的权限
     */
    public void requestPermissionInActivity(PermissionCallBack callBack, String... permissions) {
        if (mActivity == null) {
            return;
        }
        AndPermission.with(mActivity)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(data -> callBack.onGranted())
                .onDenied(data -> callBack.onDenied())
                .start();
    }

    /**
     * 申请权限(Fragment)
     *
     * @param callBack    回调
     * @param permissions 要申请的权限
     */
    public void requestPermissionInFragment(PermissionCallBack callBack, String... permissions) {
        if (mFragment == null) {
            return;
        }
        AndPermission.with(mFragment)
                .runtime()
                .permission(permissions)
                .rationale(new RuntimeRationale())
                .onGranted(data -> callBack.onGranted())
                .onDenied(data -> callBack.onDenied())
                .start();
    }

}
