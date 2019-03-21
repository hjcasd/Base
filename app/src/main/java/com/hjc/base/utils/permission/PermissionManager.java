package com.hjc.base.utils.permission;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/2/21 10:54
 * @Description: 权限管理封装类
 */
public class PermissionManager {
    private Context mContext;

    public PermissionManager(Context context) {
        this.mContext = context;
    }

    /**
     * 获取存储权限
     *
     * @param callBack 回调
     */
    public void requestStoragePermission(PermissionCallBack callBack) {
        AndPermission.with(mContext)
                .runtime()
                .permission(Permission.Group.STORAGE)
                .rationale(new RuntimeRationale())
                .onGranted(permissions -> callBack.onGranted())
                .onDenied(permissions -> {
                    if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        showSettingDialog(permissions);
                    } else {
                        callBack.onDenied();
                    }
                })
                .start();
    }

    /**
     * 获取相机权限
     *
     * @param callBack 回调
     */
    public void requestCameraPermission(PermissionCallBack callBack) {
        AndPermission.with(mContext)
                .runtime()
                .permission(Permission.Group.CAMERA)
                .rationale(new RuntimeRationale())
                .onGranted(permissions -> callBack.onGranted())
                .onDenied(permissions -> {
                    if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        showSettingDialog(permissions);
                    } else {
                        callBack.onDenied();
                    }
                })
                .start();
    }


    /**
     * 获取通讯录权限
     *
     * @param callBack 回调
     */
    public void requestContactsPermission(PermissionCallBack callBack) {
        AndPermission.with(mContext)
                .runtime()
                .permission(Permission.READ_CONTACTS)
                .rationale(new RuntimeRationale())
                .onGranted(permissions -> callBack.onGranted())
                .onDenied(permissions -> {
                    if (AndPermission.hasAlwaysDeniedPermission(mContext, permissions)) {
                        showSettingDialog(permissions);
                    } else {
                        callBack.onDenied();
                    }
                })
                .start();
    }


    /**
     * 显示去设置页面手动开启权限的dialog
     */
    private void showSettingDialog(final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(mContext, permissions);
        String message = mContext.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames));

        new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setTitle(R.string.tip)
                .setMessage(message)
                .setPositiveButton(R.string.setting, (dialog, which) -> toSetPermission(mContext, permissions))
                .setNegativeButton(R.string.cancel, (dialog, which) -> ToastUtils.showLong("请去设置页面申请权限,以便程序继续运行"))
                .show();
    }


    /**
     * 去设置页面手动开启权限
     */
    private void toSetPermission(Context context, List<String> permissions) {
        AndPermission.with(context)
                .runtime()
                .setting()
                .onComeback(() -> {
                    if (AndPermission.hasPermissions(context, permissions.toArray(new String[]{}))) {
                        ToastUtils.showShort("权限申请成功");
                    } else {
                        ToastUtils.showShort("权限申请失败,请重新去设置页面申请");
                    }
                })
                .start();
    }
}
