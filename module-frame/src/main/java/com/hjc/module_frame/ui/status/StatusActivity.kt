package com.hjc.module_frame.ui.status

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameActivityStatusBinding

/**
 * @Author: HJC
 * @Date: 2021/1/8 14:00
 * @Description: 状态布局(LoadSir)
 */
@Route(path = RouteFramePath.URL_STATUS)
class StatusActivity : BaseActivity<FrameActivityStatusBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.frame_activity_status
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
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
            R.id.btn1 -> RouteManager.jump(RouteFramePath.URL_STATUS_DEFAULT)

            R.id.btn2 -> RouteManager.jump(RouteFramePath.URL_STATUS_CUSTOM)

            else -> {
            }
        }
    }

}