package com.hjc.module_home.ui.jetpack.child

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteHomePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeActivityJetDataBinding
import com.hjc.module_home.http.entity.PersonBean

/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: DataBinding
 */
@Route(path = RouteHomePath.URL_JET_DATA_BINDING)
class JetDataActivity : BaseActivity<HomeActivityJetDataBinding, CommonViewModel>() {

    private val personBean: PersonBean = PersonBean()

    override fun getLayoutId(): Int {
        return R.layout.home_activity_jet_data
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.home_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mBindingView.personBean = personBean
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
            R.id.btn_show -> {
                personBean.name.set("哈哈哈")
                personBean.isShow.set(true)
            }

            R.id.btn_change -> {
                personBean.name.set("呵呵呵")
                personBean.isShow.set(true)
            }

            R.id.btn_hide -> personBean.isShow.set(false)

            else -> {
            }
        }
    }

}