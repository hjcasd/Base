package com.hjc.module_other.dialog

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.hjc.library_base.dialog.BaseFragmentDialog
import com.hjc.library_base.utils.ActivityHelper.finishAllActivities
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherDialogUpdateBinding
import com.hjc.module_other.utils.ApkUtils
import com.hjc.module_other.utils.update.DownloadService

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:30
 * @Description: 更新dialog
 */
class UpdateDialog : BaseFragmentDialog<OtherDialogUpdateBinding, CommonViewModel>() {

    private var apkDownloadUrl: String? = null
    private var isCompleted = false
    private var isForceUpdate = false

    companion object {
        fun newInstance(): UpdateDialog {
            return UpdateDialog()
        }
    }

    override fun getStyleRes(): Int {
       return R.style.Base_Dialog_Zoom
    }

    override fun getLayoutId(): Int {
        return R.layout.other_dialog_update
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        isCancelable = false

        apkDownloadUrl = "data4/apk/201809/06/f2a4dbd1b6cc2dca6567f42ae7a91f11_45629100.apk"
        isForceUpdate = false

        val newVersion = "1.1.0"
        val updateLog = "1.部分bug修复\r\n2.部分逻辑修改"
        val content = "当前有新版本,是否升级到" + newVersion + "版本？"

        mBindingView.tvTitle.text = content
        mBindingView.tvUpdateInfo.text = updateLog

        //检测是否已下载过APK
        if (ApkUtils.appIsDownloaded()) {
            isCompleted = true
            mBindingView.btnUpdate.text = "安装"
        } else {
            isCompleted = false
            mBindingView.btnUpdate.text = "升级"
        }
    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn_update -> {
                if (!isCompleted) { //升级
                    downloadApk()
                } else { //安装
                    ApkUtils.installApk()
                }
                dismiss()
            }

            R.id.btn_cancel -> if (isForceUpdate) {
                finishAllActivities()
            } else {
                dismiss()
            }

            else -> {
            }
        }
    }

    /**
     * 下载APK
     */
    private fun downloadApk() {
        val intent = Intent(mContext, DownloadService::class.java)
        intent.putExtra("apkUrl", apkDownloadUrl)
        mContext.startService(intent)
    }

}