package com.hjc.module_home.ui.jetpack

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteHomePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeActivityJetpackBinding

/**
 * @Author: HJC
 * @Date: 2021/1/8 10:56
 * @Description: Jetpack组件演示
 */
@Route(path = RouteHomePath.URL_JETPACK)
class JetpackActivity : BaseActivity<HomeActivityJetpackBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_jetpack
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.home_color)
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
            R.id.btn1 -> RouteManager.jump(RouteHomePath.URL_JET_DATA_BINDING)

            R.id.btn2 -> RouteManager.jump(RouteHomePath.URL_JET_LIVE_DATA)

            R.id.btn3 -> RouteManager.jump(RouteHomePath.URL_JET_BINDING_ADAPTER)

            R.id.btn4 -> RouteManager.jump(RouteHomePath.URL_JET_ROOM)

            R.id.btn5 -> RouteManager.jump(RouteHomePath.URL_JET_PAGING)

            else -> {
            }
        }
    }

}