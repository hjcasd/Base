package com.hjc.module_other.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
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
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {

    private lateinit var llRootView: View
    private lateinit var ivHide: ImageView

    init {
        initView()
        initData()
        addListener()
    }

    private fun initView() {
        llRootView = LayoutInflater.from(mContext).inflate(R.layout.other_layout_plane_info, this)
        ivHide = findViewById(R.id.iv_hide)
    }

    private fun initData() {

    }

    private fun addListener() {
        ivHide.setOnClickListener {
            EventManager.sendEvent(MessageEvent(EventCode.HIDE_PLANE_VIEW, null))
        }
    }

}