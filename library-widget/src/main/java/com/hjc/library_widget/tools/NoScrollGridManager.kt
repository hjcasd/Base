package com.hjc.library_widget.tools

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:30
 * @Description: 禁止网格RecyclerView滑动的管理器
 */
class NoScrollGridManager(context: Context, spanCount: Int) :
    GridLayoutManager(context, spanCount) {

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