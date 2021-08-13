package com.hjc.module_senior.ui.animation.child

import android.animation.AnimatorInflater
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityAnimatorXmlBinding

/**
 * @Author: HJC
 * @Date: 2021/8/13 15:26
 * @Description: 动画XML写法
 */
@Route(path = RouteSeniorPath.URL_ANIMATION_XML)
class AnimatorXmlActivity : BaseActivity<SeniorActivityAnimatorXmlBinding, CommonViewModel>() {

    /**
     * View Animator 、Drawable Animator都可以在anim文件夹下创建动画
     * 属性动画在res下建立animator文件夹，然后建立res/animator/scale_x.xml
     *
     * <animator>  对应代码中的ValueAnimator
     * <objectAnimator>  对应代码中的ObjectAnimator
     * <set>  对应代码中的AnimatorSet
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_animator_xml
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
            override fun leftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                val animator = AnimatorInflater.loadAnimator(this, R.animator.scale_x)
                animator.setTarget(mBindingView.ivTest)
                animator.start()
            }

            R.id.btn2 -> {
                // 缩放、反转等都有中心点或者轴，默认中心缩放，和中间对称线为反转线，所以我决定这个横向，纵向缩小以左上角为中心点
                val animator = AnimatorInflater.loadAnimator(this, R.animator.scale_xy)
                mBindingView.ivTest.pivotX = 0f
                mBindingView.ivTest.pivotY = 0f
                mBindingView.ivTest.invalidate()
                animator.setTarget(mBindingView.ivTest)
                animator.start()
            }

            else -> {
            }
        }
    }
}