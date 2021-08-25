package com.hjc.module_home.ui.coroutines

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.base.ILoadingView
import com.hjc.library_common.loadsir.CustomLoadingViewImpl
import com.hjc.library_common.router.path.RouteHomePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeActivityCoroutinesBinding
import com.hjc.module_home.viewmodel.coroutines.CoroutinesViewModel

/**
 * @Author: HJC
 * @Date: 2021/1/8 10:56
 * @Description: 协程
 */
@Route(path = RouteHomePath.URL_COROUTINES)
class CoroutinesActivity : BaseActivity<HomeActivityCoroutinesBinding, CoroutinesViewModel>() {

    /**
     * 最简单的协程运行模式，不涉及挂起时，谁写在前面谁先运行，后面的等前面的协程运行完之后再运行。
     * 涉及到挂起时，前面的协程挂起了，那么线程不会空闲，而是继续运行下一个协程，
     * 而前面挂起的那个协程在挂起结速后不会马上运行，而是等待当前正在运行的协程运行完毕后再去执行
     */

    override fun getLayoutId(): Int {
        return R.layout.home_activity_coroutines
    }

    override fun createViewModel(): CoroutinesViewModel {
        return ViewModelProvider(this)[CoroutinesViewModel::class.java]
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.home_color)
    }

    override fun createLoadingView(): ILoadingView {
        return CustomLoadingViewImpl(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel?.loadData()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }

        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> mViewModel?.launchTest()

            R.id.btn2 -> mViewModel?.asyncTest()

            R.id.btn3 -> mViewModel?.runBlockingTest()
        }
    }

}