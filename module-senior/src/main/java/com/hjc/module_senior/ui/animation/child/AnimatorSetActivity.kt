package com.hjc.module_senior.ui.animation.child

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityAnimatorSetBinding

/**
 * @Author: HJC
 * @Date: 2021/8/13 15:26
 * @Description: 组合动画
 */
@Route(path = RouteSeniorPath.URL_ANIMATION_SET)
class AnimatorSetActivity : BaseActivity<SeniorActivityAnimatorSetBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_animator_set
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
            R.id.btn1 -> {
                val animator1 = ObjectAnimator.ofFloat(mBindingView.ivTest, "scaleX", 1.0f, 2.0f)
                val animator2 = ObjectAnimator.ofFloat(mBindingView.ivTest, "scaleY", 1.0f, 2.0f)

                val animatorSet = AnimatorSet()
                animatorSet.duration = 2000
                animatorSet.interpolator = LinearInterpolator()
                animatorSet.playTogether(animator1, animator2)
                animatorSet.start()
            }

            R.id.btn2 -> {
                val cx: Float = mBindingView.ivTest.x

                val anim1 = ObjectAnimator.ofFloat(mBindingView.ivTest, "scaleX", 1.0f, 2f)
                val anim2 = ObjectAnimator.ofFloat(mBindingView.ivTest, "scaleY", 1.0f, 2f)
                val anim3 = ObjectAnimator.ofFloat(mBindingView.ivTest, "x", cx, 0f)
                val anim4 = ObjectAnimator.ofFloat(mBindingView.ivTest, "x", cx)

                /**
                 * anim1，anim2,anim3同时执行
                 * anim4接着执行
                 * animSet.play().with();也是支持链式编程的，但是不要想着狂点，
                 * 比如 animSet.play(anim1).with(anim2).before(anim3).before(anim5); 这样是不行的，系统不会根据你写的这一长串来决定先后的顺序，
                 * 必须像下面这种写法
                 */
                val animSet = AnimatorSet()
                animSet.play(anim1).with(anim2)
                animSet.play(anim2).with(anim3)
                animSet.play(anim4).after(anim3)
                animSet.duration = 2000
                animSet.start()
            }

            else -> {
            }
        }
    }
}