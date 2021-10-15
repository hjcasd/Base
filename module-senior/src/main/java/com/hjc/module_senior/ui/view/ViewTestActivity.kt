package com.hjc.module_senior.ui.view

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityViewTestBinding

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
        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }
}