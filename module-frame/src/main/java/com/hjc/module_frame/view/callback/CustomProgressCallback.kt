package com.hjc.module_frame.view.callback

import android.content.Context
import android.view.View
import android.widget.Button
import com.blankj.utilcode.util.ToastUtils
import com.hjc.module_frame.R
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: loadSir 等待加载
 */
class CustomProgressCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.frame_layout_loading
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return true
    }

    override fun onViewCreate(context: Context?, view: View?) {
        super.onViewCreate(context, view)
        val btnRetry = view?.findViewById<Button>(R.id.btn_retry)
        btnRetry?.setOnClickListener {
            ToastUtils.showShort("哈哈哈哈")
        }
    }
}