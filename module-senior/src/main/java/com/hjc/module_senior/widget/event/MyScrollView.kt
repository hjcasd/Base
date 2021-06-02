package com.hjc.module_senior.widget.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.blankj.utilcode.util.LogUtils

class MyScrollView constructor(context: Context, attrs: AttributeSet?) :
    ScrollView(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val result = super.onInterceptTouchEvent(ev)
        LogUtils.e("MyScrollView: onInterceptTouchEvent: $result")
        return result
    }
}