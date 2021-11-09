package com.hjc.module_senior.widget.cabin

import android.animation.TypeEvaluator
import android.graphics.RectF

/**
 * @Author: HJC
 * @Date: 2021/8/15 15:08
 * @Description: 矩形过渡的TypeEvaluator
 */
class CabinRectEvaluator : TypeEvaluator<RectF> {
    /**
     *
     * @param fraction 用于表示动画的完成度，类似于百分比
     * @param startValue 初始值
     * @param endValue 结束值
     * @return 当前变化的矩形
     */
    override fun evaluate(fraction: Float, startValue: RectF, endValue: RectF): RectF {
        val left = startValue.left + fraction * (endValue.left - startValue.left)
        val top = startValue.top + fraction * (endValue.top - startValue.top)
        val right = startValue.right + fraction * (endValue.right - startValue.right)
        val bottom = startValue.bottom + fraction * (endValue.bottom - startValue.bottom)
        return RectF(left, top, right, bottom)
    }
}