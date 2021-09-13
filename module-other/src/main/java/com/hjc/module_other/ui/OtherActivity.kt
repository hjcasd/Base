package com.hjc.module_other.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityBinding

/**
 * @Author: HJC
 * @Date: 2021/1/20 14:57
 * @Description: OtherActivity
 */
@Route(path = RouteOtherPath.URL_OTHER_ACTIVITY)
class OtherActivity : BaseActivity<OtherActivityBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
       return R.layout.other_activity
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        val fragment = ARouter.getInstance().build(RouteOtherPath.URL_OTHER_FRAGMENT).navigation() as Fragment
        supportFragmentManager.beginTransaction()
            .add(R.id.fcv_other, fragment)
            .commit()
    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }
}