package com.hjc.module_other.ui.video.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.hjc.module_other.R
import com.hjc.module_other.utils.MediaViewUtils

/**
 * @Author: HJC
 * @Date: 2021/9/27 10:55
 * @Description: 飞机信息view
 */
class PlaneInfoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var rlShowArrow: RelativeLayout
    private lateinit var rlHideArrow: RelativeLayout
    private lateinit var llInfo: LinearLayout

    init {
        LayoutInflater.from(context).inflate(R.layout.other_layout_plane_info, this)

        initView()
        addListener()
    }

    private fun initView() {
        rlShowArrow = findViewById(R.id.rl_show_arrow)
        rlHideArrow = findViewById(R.id.rl_hide_arrow)
        llInfo = findViewById(R.id.ll_info)
    }

    private fun addListener() {
        rlShowArrow.setOnClickListener {
            showPanel()
        }

        rlHideArrow.setOnClickListener {
            hidePanel()
        }
    }

    /**
     * 显示面板
     */
    fun showPanel() {
        MediaViewUtils.showRightView(llInfo, rlShowArrow)
    }

    /**
     * 显示箭头
     */
    fun showArrow() {
        rlShowArrow.visibility = View.VISIBLE
        llInfo.visibility = View.INVISIBLE
    }

    /**
     * 隐藏面板
     */
    fun hidePanel() {
        MediaViewUtils.hideRightView(llInfo, rlShowArrow)
    }

    /**
     * 隐藏整个View
     */
    fun hideAll() {
        rlShowArrow.visibility = View.GONE
        llInfo.visibility = View.INVISIBLE
    }

}