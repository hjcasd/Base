package com.hjc.module_other.ui.scroll

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_other.R
import com.hjc.module_other.ui.scroll.adapter.ScrollToppingAdapter
import com.hjc.module_other.databinding.OtherActivityScrollToppingBinding
import com.hjc.module_other.http.entity.ScrollBean
import com.hjc.module_other.widget.sticky.StickyItemDecoration

/**
 * @Author: HJC
 * @Date: 2021/9/9 19:22
 * @Description: 滑动置顶演示
 */ 
@Route(path = RouteOtherPath.URL_SCROLL_TOPPING)
class ScrollToppingActivity : BaseActivity<OtherActivityScrollToppingBinding, CommonViewModel>() {

    private lateinit var mAdapter: ScrollToppingAdapter

    override fun getLayoutId(): Int {
        return R.layout.other_activity_scroll_topping
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initView() {
        super.initView()

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mBindingView.rvList.layoutManager = manager

        mBindingView.rvList.addItemDecoration(StickyItemDecoration())

        mAdapter = ScrollToppingAdapter()
        mBindingView.rvList.adapter = mAdapter
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.other_color)
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
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }
}