package com.hjc.module_senior.widget.anim.helper;

import android.animation.TypeEvaluator;

/**
 * @Author: HJC
 * @Date: 2021/8/15 15:08
 * @Description: 坐标过渡的TypeEvaluator
 */
public class PointEvaluator implements TypeEvaluator<Point> {

    /**
     *
     * @param fraction 用于表示动画的完成度，类似于百分比
     * @param startValue 初始值
     * @param endValue 结束值
     * @return 当前变化的点
     */
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        float x = startValue.getX() + (fraction * (endValue.getX() - startValue.getX()));
        float y = startValue.getY() + (fraction * (endValue.getY() - startValue.getY()));
        return new Point(x, y);
    }
}
