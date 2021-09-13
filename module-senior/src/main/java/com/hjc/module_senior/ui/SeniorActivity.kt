package com.hjc.module_senior.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityBinding

/**
 * @Author: HJC
 * @Date: 2021/1/20 14:57
 * @Description: SeniorActivity
 */
@Route(path = RouteSeniorPath.URL_SENIOR_ACTIVITY)
class SeniorActivity : BaseActivity<SeniorActivityBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
       return R.layout.senior_activity
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        val fragment = ARouter.getInstance().build(RouteSeniorPath.URL_SENIOR_FRAGMENT).navigation() as Fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_senior, fragment)
            .commit()
    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }
}