package com.hjc.module_senior.widget.event

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.blankj.utilcode.util.LogUtils

class MyScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val result = super.onInterceptTouchEvent(ev)
        LogUtils.e("MyScrollView: onInterceptTouchEvent: $result")
        return result
    }
}