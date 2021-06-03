package com.hjc.module_senior.widget.event

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton
import com.blankj.utilcode.util.LogUtils

class MyButton constructor(context: Context, attrs: AttributeSet?) :
    AppCompatButton(context, attrs) {

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyButton: dispatchTouchEvent: " + event.action)
        return super.dispatchTouchEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        LogUtils.e("MyButton: onTouchEvent: " + event.action)
        return super.onTouchEvent(event)
    }
}