package com.hjc.module_other.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.hjc.module_other.R

class PlaneInfoView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(mContext, attrs, defStyleAttr) {

    init {
        initView()
        initData()
        addListener()
    }

    private fun initView() {
        LayoutInflater.from(mContext).inflate(R.layout.other_layout_plane_info, this)
    }

    private fun initData() {

    }

    private fun addListener() {}


}