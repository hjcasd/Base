package com.hjc.module_senior.ui.animation.child

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityAnimatorObjectBinding

/**
 * @Author: HJC
 * @Date: 2021/8/13 15:26
 * @Description: 属性动画(ObjectAnimator)
 */
@Route(path = RouteSeniorPath.URL_ANIMATION_OBJECT)
class AnimatorObjectActivity : BaseActivity<SeniorActivityAnimatorObjectBinding, CommonViewModel>() {

    /**
     * 1.提供了ofInt、ofFloat、ofObject，这几个方法都是设置动画作用的元素、作用的属性、动画开始、结束、以及中间的任意个属性值.
     * 2.对于属性值，只设置一个的时候，会认为当然对象该属性的值为开始（getPropName反射获取），然后设置的值为终点。如果设置两个，则一个为开始、一个为结束.
     * 3.动画更新的过程中，会不断调用setPropName更新元素的属性，所有使用ObjectAnimator更新某个属性，必须得有getter（设置一个属性值的时候）和setter方法.
     * 4.如果你操作对象的该属性方法里面，则你需要手动调用animator.addUpdateListener()
     * 5.实现一个动画更改多个效果：使用propertyValuesHolder
     * 6.直接对任意对象的任意属性进行动画操作的，比如说View的alpha属性
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_animator_object
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
                ObjectAnimator
                    .ofFloat(mBindingView.ivTest, "rotationX", 0f, 360f)
                    .setDuration(2000)
                    .start()
            }

            R.id.btn2 -> {
                val pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f, 1f)
                val pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0f, 1f)
                val pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f, 0f, 1f)
                ObjectAnimator.ofPropertyValuesHolder(mBindingView.ivTest, pvhX, pvhY, pvhZ)
                    .setDuration(2000)
                    .start()
            }

            else -> {
            }
        }
    }
}