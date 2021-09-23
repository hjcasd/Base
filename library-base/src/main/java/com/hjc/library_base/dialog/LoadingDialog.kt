package com.hjc.library_base.dialog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.View
import android.widget.TextView
import com.hjc.library_base.R
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2021/6/20 17:29
 * @Description: 加载框
 */
class LoadingDialog(context: Context, themeResId: Int = R.style.Base_Dialog) : Dialog(context, themeResId) {

    class Builder(private val mContext: Context) {

        /**
         * 提示信息
         */
        private var message: String? = ""

        /**
         * 是否显示提示信息
         */
        private var isShowMessage = true

        /**
         * 否可以按返回键取消
         */
        private var isCancelable = false

        /**
         * 是否可以取消
         */
        private var isCancelOutside = false


        /**
         * 设置提示信息
         */
        fun setMessage(message: String?): Builder {
            this.message = message
            return this
        }

        /**
         * 设置是否显示提示信息
         */
        fun setShowMessage(isShowMessage: Boolean): Builder {
            this.isShowMessage = isShowMessage
            return this
        }

        /**
         * 设置是否可以按返回键取消
         */
        fun setCancelable(isCancelable: Boolean): Builder {
            this.isCancelable = isCancelable
            return this
        }

        /**
         * 设置是否可以取消
         */
        fun setCancelOutside(isCancelOutside: Boolean): Builder {
            this.isCancelOutside = isCancelOutside
            return this
        }

        /**
         * 创建Dialog
         */
        fun create(): LoadingDialog {
            val view = View.inflate(mContext, R.layout.base_dialog_loading, null)
            val dialog = LoadingDialog(mContext)
            val tvTip = view.findViewById<TextView>(R.id.tv_tip)

            if (isShowMessage) {
                tvTip.text = message ?: ""
                tvTip.visibility = View.VISIBLE
            } else {
                tvTip.visibility = View.GONE
            }
            dialog.setContentView(view)
            dialog.setCancelable(isCancelable)
            dialog.setCanceledOnTouchOutside(isCancelOutside)
            return dialog
        }
    }

    /**
     * 延迟500ms关闭dialog
     */
    @SuppressLint("CheckResult")
    fun dismissDialog() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dismiss() }
    }

}