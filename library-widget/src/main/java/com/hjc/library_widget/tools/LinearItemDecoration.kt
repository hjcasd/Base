package com.hjc.library_widget.tools

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:29
 * @Description: RecyclerView的垂直水平分割线
 */
class LinearItemDecoration(context: Context, orientation: Int, drawableId: Int) :
    ItemDecoration() {

    private val mDrawable: Drawable? = ContextCompat.getDrawable(context, drawableId)

    private var mOrientation = 0

    init {
        setOrientation(orientation)
    }

    companion object {
        const val HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL
        const val VERTICAL_LIST = LinearLayoutManager.VERTICAL
    }

    private fun setOrientation(orientation: Int) {
        require(!(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST)) { "invalid orientation" }
        mOrientation = orientation
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(c: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val params = childView.layoutParams as RecyclerView.LayoutParams
            val top = childView.bottom + params.bottomMargin
            val bottom = top + mDrawable!!.intrinsicHeight

            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(c)
        }
    }

    private fun drawHorizontal(c: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop
        val bottom = parent.height - parent.paddingBottom
        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val params = childView.layoutParams as RecyclerView.LayoutParams
            val left = childView.right + params.rightMargin
            val right = left + mDrawable!!.intrinsicHeight

            mDrawable.setBounds(left, top, right, bottom)
            mDrawable.draw(c)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (mOrientation == VERTICAL_LIST) {
            val pos = parent.getChildLayoutPosition(view)
            //最后一个不添加分割线
            if (pos != parent.adapter!!.itemCount - 1) {
                outRect[0, 0, 0] = mDrawable!!.intrinsicHeight
            }
        } else {
            outRect[0, 0, mDrawable!!.intrinsicWidth] = 0
        }
    }

}