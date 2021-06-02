package com.hjc.module_senior.widget.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils

class MyView constructor(context: Context, attrs: AttributeSet?)  : View(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyView: dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyView: onTouchEvent")
        return super.onTouchEvent(event)
    }
}