package com.hjc.module_senior.widget.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils

class MyViewGroup constructor(context: Context, attrs: AttributeSet?)  : FrameLayout(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyViewGroup: dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        LogUtils.e("MyViewGroup: onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyViewGroup: onTouchEvent")
        return super.onTouchEvent(event)
    }
}