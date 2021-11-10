package com.hjc.module_senior.ui.touch.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils

class InterceptViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("InterceptViewGroup: dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        LogUtils.e("InterceptViewGroup: onInterceptTouchEvent")
        super.onInterceptTouchEvent(ev)
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("InterceptViewGroup: onTouchEvent")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> LogUtils.e("手指按下")
            MotionEvent.ACTION_MOVE -> LogUtils.e("手指移动")
            MotionEvent.ACTION_UP -> LogUtils.e("手指抬起")
        }
        super.onTouchEvent(event)
        return true
    }
}