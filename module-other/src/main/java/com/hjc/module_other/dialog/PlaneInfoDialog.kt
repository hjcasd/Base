package com.hjc.module_other.dialog

import android.content.DialogInterface
import com.hjc.library_base.dialog.BaseFragmentDialog
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import android.view.WindowManager
import android.widget.LinearLayout
import com.blankj.utilcode.util.BarUtils
import android.os.Bundle
import android.view.View
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.module_other.databinding.OtherDialogPlaneInfoBinding

/**
 * @Author: HJC
 * @Date: 2021/9/26 14:03
 * @Description: 飞机信息dialog
 */
class PlaneInfoDialog : BaseFragmentDialog<OtherDialogPlaneInfoBinding, CommonViewModel>() {

    companion object {
        fun newInstance(): PlaneInfoDialog {
            return PlaneInfoDialog()
        }
    }

    override fun getStyleRes(): Int {
        return R.style.Base_Dialog_Right
    }

    override fun getLayoutId(): Int {
        return R.layout.other_dialog_plane_info
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getWidth(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    override fun getHeight(): Int {
        return WindowManager.LayoutParams.MATCH_PARENT
    }

    override fun initView() {
        super.initView()
        val layoutParams = mBindingView.llInfo.layoutParams as LinearLayout.LayoutParams
        layoutParams.rightMargin = BarUtils.getStatusBarHeight()
        mBindingView.llInfo.layoutParams = layoutParams
    }

    override fun initData(savedInstanceState: Bundle?) {
        dialog?.window?.setDimAmount(0f)
    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.ll_left -> {
                dismiss()
            }

            else -> {
            }
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        EventManager.sendEvent(MessageEvent(EventCode.HIDE_PLANE_VIEW, null))
    }

}