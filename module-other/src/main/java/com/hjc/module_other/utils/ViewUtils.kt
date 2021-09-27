package com.hjc.module_other.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.view.View
import androidx.constraintlayout.widget.Placeholder

object ViewUtils {

    /**
     * 显示View动画
     */
    fun showRightView(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", view.width.toFloat(), 0f)
        animator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                view.visibility = View.VISIBLE
            }
        })
        animator.setDuration(500)
            .start()
    }

    /**
     * 隐藏View动画
     */
    fun hideRightView(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", 0f, view.width.toFloat())
        animator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
            }
        })
        animator.setDuration(500)
            .start()
    }

    /**
     * 显示View动画
     */
    fun showLeftView(view: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", -view.width.toFloat(), 0f)
        animator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationStart(animation: Animator?) {
                super.onAnimationStart(animation)
                view.visibility = View.VISIBLE
            }
        })
        animator.setDuration(500)
            .start()
    }

    /**
     * 隐藏View动画
     */
    fun hideLeftView(view: View, placeholderView: View) {
        val animator = ObjectAnimator.ofFloat(view, "translationX", 0f, -view.width.toFloat())
        animator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                view.visibility = View.INVISIBLE
                placeholderView.visibility = View.VISIBLE
            }
        })
        animator.setDuration(500)
            .start()
    }
}