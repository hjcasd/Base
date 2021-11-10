package com.hjc.module_senior.ui.touch.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ScrollView
import com.blankj.utilcode.util.LogUtils

class EventScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val result = super.onInterceptTouchEvent(ev)
        LogUtils.e("EventScrollView: onInterceptTouchEvent: $result")
        return result
    }
}