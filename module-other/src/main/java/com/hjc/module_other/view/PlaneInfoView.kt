package com.hjc.module_other.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.blankj.utilcode.util.BarUtils
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.module_other.R

/**
 * @Author: HJC
 * @Date: 2021/9/27 10:55
 * @Description: 飞机信息view
 */
class PlaneInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.other_layout_plane_info, this)
        val llArrow: LinearLayout = findViewById(R.id.ll_arrow)
        val llInfo: LinearLayout = findViewById(R.id.ll_info)

        val statusBarHeight = BarUtils.getStatusBarHeight()
        if (statusBarHeight > 80) {
            val layoutParams = llInfo.layoutParams as LayoutParams
            layoutParams.rightMargin = BarUtils.getStatusBarHeight()
            llInfo.layoutParams = layoutParams
        }

        llArrow.setOnClickListener {
            EventManager.sendEvent(MessageEvent(EventCode.HIDE_RIGHT_PANEL, 0))
        }
    }

}