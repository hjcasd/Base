package com.hjc.library_widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @Author: HJC
 * @Date: 2019/7/18 10:43
 * @Description: 解决ViewPager bug
 */
public class FixedViewPager extends ViewPager {

    public FixedViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }
}
