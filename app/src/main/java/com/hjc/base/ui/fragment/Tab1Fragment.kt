package com.hjc.base.ui.fragment

import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.hjc.base.R
import com.hjc.base.constant.RoutePath
import com.hjc.base.databinding.FragmentTab1Binding
import com.hjc.base.utils.helper.RouteManager
import com.hjc.baselib.fragment.BaseFragment
import com.hjc.baselib.viewmodel.CommonViewModel

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab1
 */
class Tab1Fragment : BaseFragment<FragmentTab1Binding, CommonViewModel>() {

    companion object {
        fun newInstance(): Tab1Fragment {
            return Tab1Fragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab1
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
            R.id.btn1 -> RouteManager.jumpWithTransition(mContext, RoutePath.URL_LOGIN, R.anim.slide_enter, R.anim.slide_exit)
            R.id.btn2 -> RouteManager.jump(RoutePath.URL_COROUTINES)
            else -> { }
        }
    }
}