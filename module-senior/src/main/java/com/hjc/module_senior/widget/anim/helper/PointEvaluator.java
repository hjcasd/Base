package com.hjc.module_senior.widget.anim.helper;

import android.animation.TypeEvaluator;


/**
 * 坐标过度的TypeEvaluator
 */
public class PointEvaluator implements TypeEvaluator {

    /**
     *
     * @param fraction 用于表示动画的完成度，类似于百分比
     * @param startValue 初始值
     * @param endValue 结束值
     * @return
     */

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + (fraction * (endPoint.getX() - startPoint.getX()));
        float y = startPoint.getY() + (fraction * (endPoint.getY() - startPoint.getY()));
        Point point = new Point(x, y);
        return point;
    }
}
