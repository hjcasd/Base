package com.hjc.library_widget.list.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:29
 * @Description: RecyclerView的网格分割线
 */
class GridItemDecoration(context: Context, resId: Int) : ItemDecoration() {

    //获取 Drawable 对象
    private val mDrawable: Drawable? = ContextCompat.getDrawable(context, resId)

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        //绘制水平方向
        val mChildCount = parent.childCount
        for (i in 0 until mChildCount) {
            val childView = parent.getChildAt(i)
            val layoutParams = childView.layoutParams as RecyclerView.LayoutParams

            val left = childView.left - layoutParams.leftMargin
            val right = childView.right + mDrawable!!.intrinsicWidth + layoutParams.rightMargin
            val top = childView.bottom + layoutParams.bottomMargin
            val bottom = top + mDrawable.intrinsicHeight

            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(canvas)
        }

        //绘制垂直方向
        for (i in 0 until mChildCount) {
            val childView = parent.getChildAt(i)
            val layoutParams = childView.layoutParams as RecyclerView.LayoutParams

            val left = childView.right + layoutParams.rightMargin
            val right = left + mDrawable!!.intrinsicWidth
            val top = childView.top - layoutParams.topMargin
            val bottom = childView.bottom + layoutParams.bottomMargin

            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(canvas)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        var bottom = mDrawable!!.intrinsicHeight
        var right = mDrawable.intrinsicWidth
        //如果是最后一列，留出位置
        if (isLastCol(view, parent)) {
            right = 0
        }
        // 如果是最后一行，留出位置
        if (isLastRow(view, parent)) {
            bottom = 0
        }
        outRect.bottom = bottom
        outRect.right = right
    }

    /**
     * 是否是最后一列
     * （当前位置+1）% 列数 ==0，判断是否为最后一列
     */
    private fun isLastCol(view: View, parent: RecyclerView): Boolean {
        val currentPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val spanCount = getSpanCount(parent)
        return (currentPosition + 1) % spanCount == 0
    }

    /**
     * 是否是最后一行
     * 当前位置+1 > (行数-1)*列数
     */
    private fun isLastRow(view: View, parent: RecyclerView): Boolean {
        val currentPosition = (view.layoutParams as RecyclerView.LayoutParams).viewLayoutPosition
        val spanCount = getSpanCount(parent)

        val rowNum =
            if (parent.adapter!!.itemCount / spanCount == 0) parent.adapter!!.itemCount / spanCount else parent.adapter!!.itemCount / spanCount + 1
        return currentPosition + 1 > (rowNum - 1) * spanCount
    }

    /**
     * 获取列数
     * 如果是GridView，就获取列数，如果是ListView，列数就是1
     */
    private fun getSpanCount(parent: RecyclerView): Int {
        val layoutManager = parent.layoutManager
        if (layoutManager is GridLayoutManager) {
            return layoutManager.spanCount
        }
        return 1
    }

}