package com.hjc.module_senior.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RoutePath
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityBinding

/**
 * @Author: HJC
 * @Date: 2021/1/20 14:57
 * @Description: SeniorActivity
 */
@Route(path = RoutePath.Senior.URL_SENIOR_ACTIVITY)
class SeniorActivity : BaseActivity<SeniorActivityBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
       return R.layout.senior_activity
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