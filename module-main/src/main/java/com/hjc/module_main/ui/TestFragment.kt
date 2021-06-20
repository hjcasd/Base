package com.hjc.module_main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_main.R
import com.hjc.module_main.databinding.MainFragmentTestBinding

/**
 * @Author: HJC
 * @Date: 2021/2/2 20:39
 * @Description: 测试fragment
 */
class TestFragment : BaseFragment<MainFragmentTestBinding, CommonViewModel>() {

    companion object {

        fun getInstance(): Fragment {
            return TestFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.main_fragment_test
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.main_color)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }
}