package com.hjc.module_senior.ui.view

import android.graphics.RectF
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
import com.hjc.module_senior.databinding.SeniorActivityViewTestBinding
import com.hjc.module_senior.widget.CabinView

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 测试View
 */
@Route(path = RouteSeniorPath.URL_VIEW_TEST)
class ViewTestActivity : BaseActivity<SeniorActivityViewTestBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_view_test
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
            R.id.btn_test -> {
                val rectF = RectF(200f, 400f, 400f, 800f)
                mBindingView.cabinView.startAnimation(rectF)
            }

            else -> {
            }
        }
    }
}