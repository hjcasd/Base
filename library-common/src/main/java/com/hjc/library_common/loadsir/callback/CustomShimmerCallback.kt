package com.hjc.library_common.loadsir.callback

import android.content.Context
import android.view.View
import com.hjc.library_common.R
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: 自定义 loadSir 骨架屏加载页面
 */
class CustomShimmerCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.common_layout_custom_shimmer
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return true
    }
}