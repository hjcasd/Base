package com.hjc.module_senior.ui.animation.child

import android.animation.ValueAnimator
import android.graphics.PointF
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityAnimatorValueBinding

/**
 * @Author: HJC
 * @Date: 2021/8/13 15:26
 * @Description: 属性动画(ValueAnimator)
 */
@Route(path = RouteSeniorPath.URL_ANIMATION_VALUE)
class AnimatorValueActivity : BaseActivity<SeniorActivityAnimatorValueBinding, CommonViewModel>() {

    /**
     * 1.不需要操作的对象的属性一定要有getter和setter方法，你可以自己根据当前动画的计算值，来操作任何属性
     * 2.ValueAnimator对值进行了一个平滑的动画过渡
     */

    private var screenHeight: Int = 0

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_animator_value
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.senior_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val resources = this.resources
        val dm = resources.displayMetrics
        screenHeight = dm.heightPixels
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                val valueAnimator = ValueAnimator.ofFloat(0f, (screenHeight - mBindingView.ivTest.height).toFloat())
                valueAnimator.duration = 2000
                valueAnimator.start()
                //更改对应属性的值
                valueAnimator.addUpdateListener { animation ->
                    //垂直平移
                    val value = animation.animatedValue as Float
                    mBindingView.ivTest.translationY = value
                }
            }

            R.id.btn2 -> {
                val valueAnimator = ValueAnimator()
                valueAnimator.duration = 3000
                valueAnimator.setObjectValues(PointF(0f, 0f))
                valueAnimator.interpolator = LinearInterpolator()
                valueAnimator.setEvaluator { fraction, startValue, endValue ->
                    LogUtils.e("startValue: $startValue, endValue: $endValue")
                    // fraction = t / duration
                    // x方向200px/s ，则y方向0.5 * 10 * t
                    val point = PointF()
                    point.x = 200 * fraction * 3
                    point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3)
                    point
                }

                valueAnimator.start()
                valueAnimator.addUpdateListener { animation ->
                    val point = animation.animatedValue as PointF
                    mBindingView.ivTest.x = point.x
                    mBindingView.ivTest.y = point.y
                }
            }

            else -> {
            }
        }
    }
}