package com.hjc.module_senior.ui.view

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityViewPictureBinding

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义View
 */
@Route(path = RoutePath.Senior.URL_VIEW_PICTURE)
class ViewPictureActivity : BaseActivity<SeniorActivityViewPictureBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_view_picture
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
            R.id.btn1 -> mBindingView.customView.draw(1)

            R.id.btn2 -> mBindingView.customView.draw(2)

            R.id.btn3 -> mBindingView.customView.draw(3)

            else -> {
            }
        }
    }
}