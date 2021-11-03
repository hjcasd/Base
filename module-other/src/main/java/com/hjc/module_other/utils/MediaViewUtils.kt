package com.hjc.module_other.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View

object MediaViewUtils {

    /**
     * 显示View动画
     */
    fun showRightView(view: View, otherView: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat(), 0f)
        animator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                view.visibility = View.VISIBLE
                otherView.visibility = View.GONE
            }
        })
        animator.setDuration(500)
            .start()
    }

    /**
     * 隐藏View动画
     */
    fun hideRightView(view: View, otherView: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", 0f, view.width.toFloat())
        animator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
                otherView.visibility = View.VISIBLE
            }
        })
        animator.setDuration(500)
            .start()
    }

    /**
     * 显示左侧View动画
     */
    fun showLeftView(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", 0f)
        animator.setDuration(500)
            .start()
    }

    /**
     * 隐藏左侧View动画
     */
    fun hideLeftView(view: View, value: Float) {
        // translationX :表示在 X 轴上的平移距离,以当前控件为原点，向右为正方向，参数 translationX 表示移动的距离
        val animator = ObjectAnimator.ofFloat(view, "translationX", -value)
        animator.setDuration(500)
            .start()
    }

}