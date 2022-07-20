package com.hjc.module_frame.utils

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.LogUtils
import com.hjc.module_frame.adapter.ArticleAdapter


class MyItemTouchCallBack constructor(private val mAdapter: ArticleAdapter) : ItemTouchHelper.Callback() {

    /**
     * 设置支持的拖动或者侧滑的方向
     */
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        // 上下拖动
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        // 左右侧滑
        val swipeFlags = ItemTouchHelper.ACTION_STATE_IDLE
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    /**
     * 是否开启长按拖动
     */
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    /**
     * 是否开启左右侧滑
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return false
    }

    /**
     * 拖动的回调
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        LogUtils.e("from position: " + viewHolder.absoluteAdapterPosition)
        LogUtils.e("to position: " + target.absoluteAdapterPosition)

        mAdapter.onItemMove(viewHolder.absoluteAdapterPosition, target.absoluteAdapterPosition)
        return true
    }

    /**
     * 侧滑的回调
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    /**
     * 触发拖动的百分比
     * 针对drag状态，当滑动超过多少就可以触发onMove()方法(这里指onMove()方法的调用，并不是随手指移动的View)
     */
    override fun getMoveThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        return super.getMoveThreshold(viewHolder)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
//        LogUtils.e("dy: $dY")
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

}