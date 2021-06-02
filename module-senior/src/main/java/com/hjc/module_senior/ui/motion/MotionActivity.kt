package com.hjc.module_senior.ui.motion

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityMotionBinding

@Route(path = RoutePath.Senior.URL_MOTION)
class MotionActivity : BaseActivity<SeniorActivityMotionBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_motion
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
            R.id.btn1 -> RouteManager.jump(RoutePath.Senior.URL_TOUCH)
            R.id.btn2 -> RouteManager.jump(RoutePath.Senior.URL_VIEW)
            else -> {
            }
        }
    }
}