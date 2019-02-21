package com.hjc.base.ui.update;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hjc.base.R;
import com.hjc.base.base.dialog.BaseDialog;
import com.hjc.base.constant.AppConstants;
import com.hjc.base.model.response.VersionBean;
import com.hjc.base.ui.update.download.DownloadService;
import com.hjc.base.ui.update.utils.ApkUtils;
import com.hjc.base.utils.helper.ActivityManager;

import java.io.File;

import butterknife.BindView;

public class UpdateDialog extends BaseDialog {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_update_info)
    TextView tvUpdateInfo;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private VersionBean mVersionBean;
    private String apkDownloadUrl;
    private boolean isCompleted = false;

    private boolean isForceUpdate;

    public static UpdateDialog newInstance(Bundle bundle) {
        UpdateDialog fragment = new UpdateDialog();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public int getStyleRes() {
        return R.style.Dialog_Base;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_update;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setCancelable(false);

        Bundle bundle = getArguments();
        mVersionBean = (VersionBean) getArguments().getSerializable("version");
        isForceUpdate = bundle.getBoolean("isForceUpdate");

        apkDownloadUrl = mVersionBean.getFilePath();
        apkDownloadUrl = "data4/apk/201809/06/f2a4dbd1b6cc2dca6567f42ae7a91f11_45629100.apk";

        String newVersion = mVersionBean.getNewVersion();
        String updateLog = mVersionBean.getUpdateLog();

        tvTitle.setText("当前有新版本,是否升级到" + newVersion + "版本？");
        tvUpdateInfo.setText(updateLog);

        //检测是否已下载过APK
        if (ApkUtils.appIsDownloaded()) {
            isCompleted = true;
            btnUpdate.setText("安装");
        } else {
            isCompleted = false;
            btnUpdate.setText("升级");
        }
    }

    @Override
    public void addListeners() {
        btnUpdate.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

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
                if (isForceUpdate){
                    ActivityManager.finishAllActivities();
                }else{
                    dismiss();
                }
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