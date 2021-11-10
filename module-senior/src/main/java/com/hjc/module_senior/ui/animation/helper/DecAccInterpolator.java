package com.hjc.module_senior.ui.animation.helper;

import android.animation.TimeInterpolator;

/**
 * @Author: HJC
 * @Date: 2021/8/15 15:07
 * @Description: 先加速后减速的动画插值器
 */
public class DecAccInterpolator implements TimeInterpolator {

    @Override
    public float getInterpolation(float input) {
        float result;
        if (input <= 0.5) {
            result = (float) (Math.sin(Math.PI * input)) / 2;
        } else {
            result = (float) (2 - Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }
}
