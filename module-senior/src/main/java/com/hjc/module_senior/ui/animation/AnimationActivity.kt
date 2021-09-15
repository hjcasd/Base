package com.hjc.module_senior.ui.animation

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityAnimationBinding

/**
 * @Author: HJC
 * @Date: 2021/8/13 15:26
 * @Description: 动画详解
 */
@Route(path = RouteSeniorPath.URL_ANIMATION)
class AnimationActivity : BaseActivity<SeniorActivityAnimationBinding, CommonViewModel>() {

    /**
     * 定义:动画的执行类来设置动画操作的对象的属性、持续时间，开始和结束的属性值，时间差值等，然后系统会根据设置的参数动态的变化对象的属性.
     * 属性介绍
     * Duration: 动画的持续时间，默认300ms.
     *
     * Time interpolation：时间差值,LinearInterpolator、AccelerateDecelerateInterpolator,定义动画的变化率.
     *
     * Repeat count and behavior：重复次数、以及重复模式；可以定义重复多少次；重复时从头开始，还是反向.
     *
     * Animator sets: 动画集合，你可以定义一组动画，一起执行或者顺序执行。
     *
     * Frame refresh delay：帧刷新延迟，对于你的动画，多久刷新一次帧；默认为10ms，但最终依赖系统的当前状态；基本不用管.
     *
     * 相关的类
     *
     * ObjectAnimator  动画的执行类.
     *
     * ValueAnimator 动画的执行类.
     *
     * AnimatorSet 用于控制一组动画的执行：线性，一起，每个动画的先后执行等。
     *
     * AnimatorInflater 用户加载属性动画的xml文件
     *
     * TypeEvaluator  类型估值，主要用于设置动画操作属性的值。
     *
     * TimeInterpolator 时间插值.
     *
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_animation
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
            R.id.btn1 -> RouteManager.jump(RouteSeniorPath.URL_ANIMATION_OBJECT)

            R.id.btn2 -> RouteManager.jump(RouteSeniorPath.URL_ANIMATION_VALUE)

            R.id.btn3 -> RouteManager.jump(RouteSeniorPath.URL_ANIMATION_SET)

            R.id.btn4 -> RouteManager.jump(RouteSeniorPath.URL_ANIMATION_XML)

            R.id.btn5 -> RouteManager.jump(RouteSeniorPath.URL_ANIMATION_INTERPOLATOR)

            else -> {
            }
        }
    }
}