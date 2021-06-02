package com.hjc.library_widget.tools

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:31
 * @Description: 禁止水平,垂直RecyclerView滑动的管理器
 */
class NoScrollLinearManager(context: Context) : LinearLayoutManager(context) {

    private var isScrollEnabled = true

    fun setScrollEnabled(flag: Boolean) {
        isScrollEnabled = flag
    }

    override fun canScrollVertically(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }

    override fun canScrollHorizontally(): Boolean {
        return isScrollEnabled && super.canScrollVertically()
    }
}