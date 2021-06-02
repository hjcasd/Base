package com.hjc.module_senior.ui.theme

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.utils.ActivityHelper
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityThemeBinding

@Route(path = RoutePath.Senior.URL_THEME)
class ThemeActivity : BaseActivity<SeniorActivityThemeBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_theme
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
        if (v?.id == R.id.btn_switch) {
            val mode: Int = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            if (mode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            ActivityHelper.finishAllActivities()
            RouteManager.jumpWithTransition(
                this,
                RoutePath.Main.URL_SPLASH,
                R.anim.base_fade_in,
                R.anim.base_fade_out
            )
            finish()
        }
    }
}