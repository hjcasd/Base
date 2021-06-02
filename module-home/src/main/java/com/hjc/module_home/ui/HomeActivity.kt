package com.hjc.module_home.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RoutePath
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeActivityBinding

/**
 * @Author: HJC
 * @Date: 2021/1/20 14:57
 * @Description: HomeActivity
 */
@Route(path = RoutePath.Home.URL_HOME_ACTIVITY)
class HomeActivity : BaseActivity<HomeActivityBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
       return R.layout.home_activity
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }
}