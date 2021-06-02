package com.hjc.module_senior.widget.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils

class MyViewGroup2 constructor(context: Context, attrs: AttributeSet?)  : FrameLayout(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyViewGroup2: dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        LogUtils.e("MyViewGroup2: onInterceptTouchEvent")
        super.onInterceptTouchEvent(ev)
        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyViewGroup2: onTouchEvent")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> LogUtils.e("手指按下")
            MotionEvent.ACTION_MOVE -> LogUtils.e("手指移动")
            MotionEvent.ACTION_UP -> LogUtils.e("手指抬起")
        }
        super.onTouchEvent(event)
        return true
    }
}