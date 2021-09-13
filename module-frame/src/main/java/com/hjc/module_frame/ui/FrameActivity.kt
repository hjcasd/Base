package com.hjc.module_frame.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_common.router.path.RouteHomePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameActivityBinding

/**
 * @Author: HJC
 * @Date: 2021/1/20 14:59
 * @Description: FrameActivity
 */
@Route(path = RouteFramePath.URL_FRAME_ACTIVITY)
class FrameActivity : BaseActivity<FrameActivityBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
       return R.layout.frame_activity
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        val fragment = ARouter.getInstance().build(RouteFramePath.URL_FRAME_FRAGMENT).navigation() as Fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_frame, fragment)
            .commit()
    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }
}