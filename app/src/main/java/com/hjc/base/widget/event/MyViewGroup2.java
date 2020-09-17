package com.hjc.base.widget.event;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;

public class MyViewGroup2 extends FrameLayout {
    public MyViewGroup2(@NonNull Context context) {
        super(context);
    }

    public MyViewGroup2(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        LogUtils.e("MyViewGroup2: dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        LogUtils.e("MyViewGroup2: onInterceptTouchEvent");
        super.onInterceptTouchEvent(ev);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("MyViewGroup2: onTouchEvent");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("手指按下");
                break;

            case MotionEvent.ACTION_MOVE:
                LogUtils.e("手指移动");
                break;

            case MotionEvent.ACTION_UP:
                LogUtils.e("手指抬起");
                break;
        }
        super.onTouchEvent(event);
        return true;
    }
}
