package com.hjc.base.ui.fragment

import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.hjc.base.R
import com.hjc.base.constant.RoutePath
import com.hjc.base.databinding.FragmentTab2Binding
import com.hjc.base.utils.helper.RouteManager
import com.hjc.baselib.fragment.BaseFragment
import com.hjc.baselib.viewmodel.CommonViewModel

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab2
 */
class Tab2Fragment : BaseFragment<FragmentTab2Binding, CommonViewModel>() {

    companion object {
        fun newInstance(): Tab2Fragment {
            return Tab2Fragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab2
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.app_color)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> RouteManager.jump(RoutePath.URL_LIST)
            R.id.btn2 -> RouteManager.jumpToWeb("百度一下", "https://www.baidu.com")
            else -> { }
        }
    }
}