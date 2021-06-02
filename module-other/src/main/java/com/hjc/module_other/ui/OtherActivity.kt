package com.hjc.module_other.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RoutePath
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityBinding

/**
 * @Author: HJC
 * @Date: 2021/1/20 14:57
 * @Description: OtherActivity
 */
@Route(path = RoutePath.Other.URL_OTHER_ACTIVITY)
class OtherActivity : BaseActivity<OtherActivityBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
       return R.layout.other_activity
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