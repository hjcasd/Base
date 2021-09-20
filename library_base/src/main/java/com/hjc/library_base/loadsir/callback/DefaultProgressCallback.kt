package com.hjc.library_base.loadsir.callback

import android.content.Context
import android.view.View
import com.hjc.library_base.R
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: loadSir 等待加载
 */
class DefaultProgressCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.base_layout_default_progress
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return true
    }
}