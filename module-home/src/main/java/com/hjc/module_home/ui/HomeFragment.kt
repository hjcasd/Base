package com.hjc.module_home.ui

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.RoutePath
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeFragmentBinding

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: HomeFragment
 */
@Route(path = RoutePath.Home.URL_HOME_FRAGMENT)
class HomeFragment : BaseFragment<HomeFragmentBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.home_fragment
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this).statusBarView(mBindingView.statusView)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> RouteManager.jump(RoutePath.Home.URL_DATA_BINDING)
            R.id.btn2 -> RouteManager.jump(RoutePath.Home.URL_LIVE_DATA)
            R.id.btn3 -> RouteManager.jump(RoutePath.Home.URL_BINDING_ADAPTER)
            R.id.btn4 -> RouteManager.jump(RoutePath.Home.URL_ROOM)
            R.id.btn5 -> RouteManager.jump(RoutePath.Home.URL_PAGING)
            R.id.btn6 -> RouteManager.jump(RoutePath.Home.URL_COROUTINES)
            else -> {
            }
        }
    }
}