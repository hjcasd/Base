package com.hjc.module_other.ui.scroll

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityScrollBinding
import com.hjc.module_other.viewmodel.AudioViewModel

/**
 * @Author: HJC
 * @Date: 2021/7/7 22:44
 * @Description: 常见列表效果演示
 */
@Route(path = RouteOtherPath.URL_SCROLL)
class ScrollActivity : BaseActivity<OtherActivityScrollBinding, AudioViewModel>() {


    override fun getLayoutId(): Int {
        return R.layout.other_activity_scroll
    }

    override fun createViewModel(): AudioViewModel {
        return ViewModelProvider(this)[AudioViewModel::class.java]
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.other_color)
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
            R.id.btn1 -> RouteManager.jump(RouteOtherPath.URL_SCROLL_TOPPING)

            R.id.btn2 -> RouteManager.jump(RouteOtherPath.URL_SCROLL_INDICATOR)

            else -> {
            }
        }
    }

}