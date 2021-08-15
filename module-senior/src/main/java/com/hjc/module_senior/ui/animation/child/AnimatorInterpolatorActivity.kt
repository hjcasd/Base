package com.hjc.module_senior.ui.animation.child

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityAnimatorInterpolatorBinding

/**
 * @Author: HJC
 * @Date: 2021/8/13 15:26
 * @Description: Interpolator插值器
 */
@Route(path = RouteSeniorPath.URL_ANIMATION_INTERPOLATOR)
class AnimatorInterpolatorActivity : BaseActivity<SeniorActivityAnimatorInterpolatorBinding, CommonViewModel>() {

    /**
     * 主要作用是可以控制动画的变化速率
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_animator_interpolator
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

    }
}