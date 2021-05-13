package com.hjc.base.ui.fragment

import android.os.Bundle
import android.view.View
import com.gyf.immersionbar.ImmersionBar
import com.hjc.base.R
import com.hjc.base.databinding.FragmentTab4Binding
import com.hjc.baselib.fragment.BaseFragment
import com.hjc.baselib.viewmodel.CommonViewModel

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab3
 */
class Tab4Fragment : BaseFragment<FragmentTab4Binding, CommonViewModel>() {

    companion object {
        fun newInstance(): Tab4Fragment {
            return Tab4Fragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_tab4
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

    }
}