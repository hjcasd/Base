package com.hjc.library_common.loadsir.callback

import android.content.Context
import android.view.View
import android.widget.Button
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.R
import com.hjc.library_common.global.EventCode
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: 自定义 loadSir 网络超时页面
 */
class CustomTimeoutCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.common_layout_custom_timeout
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }

    override fun onViewCreate(context: Context?, view: View?) {
        super.onViewCreate(context, view)
        val btnRetry = view?.findViewById<Button>(R.id.btn_retry)
        btnRetry?.setOnClickListener {
            EventManager.sendEvent(MessageEvent(EventCode.LOAD_SIR_RETRY, null))
        }
    }
}