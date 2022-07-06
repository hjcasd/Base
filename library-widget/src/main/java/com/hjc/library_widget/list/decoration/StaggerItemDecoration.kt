package com.hjc.library_widget.list.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:29
 * @Description: RecyclerView瀑布流分割线
 */
class StaggerItemDecoration(private val mDivider: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex

        if (spanIndex % 2 == 0) {
            outRect.left = 0
        } else {
            outRect.left = mDivider
        }
        // 下方间隔
        outRect.bottom = mDivider
    }

}