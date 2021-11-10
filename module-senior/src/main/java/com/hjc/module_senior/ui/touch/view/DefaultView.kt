package com.hjc.module_senior.ui.touch.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.blankj.utilcode.util.LogUtils

class DefaultView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("DefaultView: dispatchTouchEvent")
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("DefaultView: onTouchEvent")
        return super.onTouchEvent(event)
    }
}