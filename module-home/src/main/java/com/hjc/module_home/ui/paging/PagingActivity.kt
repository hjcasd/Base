package com.hjc.module_home.ui.paging

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteHomePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_home.R
import com.hjc.module_home.adapter.ConcertAdapter
import com.hjc.module_home.databinding.HomeActivityPageBinding
import com.hjc.module_home.viewmodel.paging.PageViewModel

/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: Paging
 */
@Route(path = RouteHomePath.URL_PAGING)
class PagingActivity : BaseActivity<HomeActivityPageBinding, PageViewModel>() {

    private var mAdapter: ConcertAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.home_activity_page
    }

    override fun createViewModel(): PageViewModel {
      return  ViewModelProvider(this).get(PageViewModel::class.java)
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.home_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mAdapter = ConcertAdapter()
        mBindingView.rvConcert.adapter = mAdapter
        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBindingView.rvConcert.layoutManager = manager
    }

    override fun observeLiveData() {
        mViewModel?.run {
            convertList.observe(this@PagingActivity) { concertBeans -> mAdapter?.submitList(concertBeans) }
        }
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {}
}