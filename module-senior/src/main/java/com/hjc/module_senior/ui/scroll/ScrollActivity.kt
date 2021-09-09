package com.hjc.module_senior.ui.scroll

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.adapter.ScrollAdapter
import com.hjc.module_senior.databinding.SeniorActivityScrollBinding
import com.hjc.module_senior.http.ScrollBean
import com.hjc.module_senior.widget.sticky.StickyItemDecoration

/**
 * @Author: HJC
 * @Date: 2021/9/9 19:22
 * @Description: 
 */ 
@Route(path = RouteSeniorPath.URL_SCROLL)
class ScrollActivity : BaseActivity<SeniorActivityScrollBinding, CommonViewModel>() {

    private lateinit var mAdapter: ScrollAdapter

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_scroll
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initView() {
        super.initView()

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBindingView.rvList.layoutManager = manager

        mBindingView.rvList.addItemDecoration(StickyItemDecoration())

        mAdapter = ScrollAdapter()
        mBindingView.rvList.adapter = mAdapter
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.senior_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val list = mutableListOf<ScrollBean>()

        list.add(ScrollBean("标题1", 0))
        for (i in 1..10){
            list.add(ScrollBean("1===内容${i}", 1))
        }

        list.add(ScrollBean("标题2", 0))
        for (i in 1..10){
            list.add(ScrollBean("2===内容${i}", 1))
        }

        list.add(ScrollBean("标题3", 0))
        for (i in 1..10){
            list.add(ScrollBean("3===内容${i}", 1))
        }

        list.add(ScrollBean("标题4", 0))
        for (i in 1..10){
            list.add(ScrollBean("4===内容${i}", 1))
        }

        mAdapter.setNewInstance(list)
    }

    override fun addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }
}