package com.hjc.module_other.view.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.hjc.module_other.R

/**
 * @Author: HJC
 * @Date: 2021/6/16 10:00
 * @Description: 录音Dialog
 */
class RecordDialog(private val mContext: Context) {

    private var mDialog: Dialog? = null

    private lateinit var ivRecord: ImageView
    private lateinit var ivVoice: ImageView
    private lateinit var tvMsg: TextView

    /**
     * 显示dialog
     */
    fun show() {
        val view = View.inflate(mContext, R.layout.other_dialog_record, null)
        ivRecord = view.findViewById(R.id.iv_record)
        ivVoice = view.findViewById(R.id.iv_voice)
        tvMsg = view.findViewById(R.id.tv_msg)

        mDialog = Dialog(mContext, R.style.Other_Audio_Dialog)
        mDialog?.setContentView(view)
        mDialog?.show()
    }

    /**
     * 隐藏dialog
     */
    fun dismiss() {
        mDialog?.let {
            if (it.isShowing) {
                it.dismiss()
                mDialog = null
            }
        }
    }

    /**
     * 上滑取消
     */
    fun showWantToCancel() {
        mDialog?.let {
            if (it.isShowing) {
                ivRecord.setImageResource(R.mipmap.other_icon_cancel)
                tvMsg.text = mContext.getString(R.string.other_record_release_finger_to_cancel)
            }
        }
    }

    /**
     * 设置正在录音时的dialog界面
     */
    fun showRecording() {
        mDialog?.let {
            if (it.isShowing) {
                ivRecord.setImageResource(R.mipmap.other_icon_recorder)
                tvMsg.text =  mContext.getString(R.string.other_record_finger_stroke_to_cancel)
            }
        }
    }

    /**
     * 录音时间过短
     */
    fun showTooShort() {
        mDialog?.let {
            if (it.isShowing) {
                ivRecord.setImageResource(R.mipmap.other_icon_too_short)
                tvMsg.text =  mContext.getString(R.string.other_record_too_short)
            }
        }
    }

    /**
     * 通过level更新voice上的图片
     *
     * @param db 分贝
     */
    @SuppressLint("CheckResult")
    fun updateVoiceImage(db: Double) {
        mDialog?.let {
            if (it.isShowing) {
                val value = db.toInt()
                val level: Int = when {
                    value < 20 -> 1
                    value < 40 -> 2
                    value < 60 -> 3
                    value < 80 -> 4
                    value < 100 -> 5
                    value < 120 -> 6
                    else -> 7
                }
                val resId = mContext.resources.getIdentifier("other_img_v$level", "mipmap", mContext.packageName)
                ivVoice.setBackgroundResource(resId)
            }
        }
    }
}