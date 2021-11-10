package com.hjc.module_senior.ui.touch.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import com.blankj.utilcode.util.LogUtils

class DefaultViewGroup @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("DefaultViewGroup: dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        LogUtils.e("DefaultViewGroup: onInterceptTouchEvent")
        return super.onInterceptTouchEvent(ev)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("DefaultViewGroup: onTouchEvent")
        return super.onTouchEvent(event)
    }
}