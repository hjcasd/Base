package com.hjc.module_senior.ui.touch.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.blankj.utilcode.util.LogUtils

class EventButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("EventButton: dispatchTouchEvent: " + event.action)
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("EventButton: onTouchEvent: " + event.action)
        return super.onTouchEvent(event)
    }
}