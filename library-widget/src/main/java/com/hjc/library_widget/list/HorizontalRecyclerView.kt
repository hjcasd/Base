package com.hjc.library_widget.list

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * @Author: HJC
 * @Date: 2021/7/23 10:33
 * @Description: 解决垂直RecyclerView嵌套水平RecyclerView横向滑动不流畅问题
 */
class HorizontalRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {
    private var lastX = 0
    private var lastY = 0

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val x = ev.rawX.toInt()
        val y = ev.rawY.toInt()
        var dealtX = 0
        var dealtY = 0

        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                // 保证子View能够接收到Action_move事件
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                dealtX += abs(x - lastX)
                dealtY += abs(y - lastY)
                // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (dealtX >= dealtY) {
                    parent.requestDisallowInterceptTouchEvent(true)
                } else {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
                lastX = x
                lastY = y
            }
            MotionEvent.ACTION_CANCEL -> {
            }
            MotionEvent.ACTION_UP -> {
            }
        }

        return super.dispatchTouchEvent(ev)
    }
}