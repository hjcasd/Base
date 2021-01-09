package com.hjc.base.widget.dialog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.constant.AppConstants;
import com.hjc.base.databinding.DialogUpdateBinding;
import com.hjc.base.service.DownloadService;
import com.hjc.base.utils.ApkUtils;
import com.hjc.baselib.dialog.BaseFragmentDialog;
import com.hjc.baselib.utils.ActivityHelper;
import com.hjc.baselib.viewmodel.CommonViewModel;

import java.io.File;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:30
 * @Description: 更新dialog
 */
public class UpdateDialog extends BaseFragmentDialog<DialogUpdateBinding, CommonViewModel> {
    private String apkDownloadUrl;

    private boolean isCompleted = false;
    private boolean isForceUpdate = false;

    public static UpdateDialog newInstance() {
        return new UpdateDialog();
    }


    @Override
    public int getStyleRes() {
        return R.style.Dialog_Action_Sheet;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_update;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setCancelable(false);

        apkDownloadUrl = "data4/apk/201809/06/f2a4dbd1b6cc2dca6567f42ae7a91f11_45629100.apk";
        isForceUpdate = false;

        String newVersion = "1.1.0";
        String updateLog = "1.部分bug修复\r\n2.部分逻辑修改";

        String content = "当前有新版本,是否升级到" + newVersion + "版本？";
        mBindingView.tvTitle.setText(content);
        mBindingView.tvUpdateInfo.setText(updateLog);

        //检测是否已下载过APK
        if (ApkUtils.appIsDownloaded()) {
            isCompleted = true;
            mBindingView.btnUpdate.setText("安装");
        } else {
            isCompleted = false;
            mBindingView.btnUpdate.setText("升级");
        }
    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            //升级安装
            case R.id.btn_update:
                if (!isCompleted) { //升级
                    downloadApk();
                } else { //安装
                    installApk();
                }
                dismiss();
                break;

            //取消升级
            case R.id.btn_cancel:
                if (isForceUpdate) {
                    ActivityHelper.finishAllActivities();
                } else {
                    dismiss();
                }
                break;

            default:
                break;
        }
    }

    /**
     * 下载APK
     */
    private void downloadApk() {
        Intent intent = new Intent(mContext, DownloadService.class);
        intent.putExtra("apkUrl", apkDownloadUrl);
        mContext.startService(intent);
    }

    /**
     * 安装APK
     */
    private void installApk() {
        File file = new File(AppConstants.DOWNLOAD_APK_PATH);
        ApkUtils.installApp(mContext, file);
    }
}
