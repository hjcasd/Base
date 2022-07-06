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
import com.hjc.module_other.databinding.OtherActivityScrollIndicatorBinding
import com.hjc.module_other.http.entity.IndicatorBean
import com.hjc.module_other.ui.scroll.adapter.ScrollOuterAdapter

/**
 * @Author: HJC
 * @Date: 2021/9/9 19:22
 * @Description: 水平滑动指示器演示
 */
@Route(path = RouteOtherPath.URL_SCROLL_INDICATOR)
class ScrollIndicatorActivity : BaseActivity<OtherActivityScrollIndicatorBinding, CommonViewModel>() {

    private lateinit var mAdapter: ScrollOuterAdapter

    override fun getLayoutId(): Int {
        return R.layout.other_activity_scroll_indicator
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initView() {
        super.initView()

        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        mBindingView.rvList.layoutManager = manager

        mAdapter = ScrollOuterAdapter()
        mBindingView.rvList.adapter = mAdapter

        mBindingView.indicatorView.bindRecyclerView(mBindingView.rvList)
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.other_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val list = mutableListOf<IndicatorBean>()
        for (i in 0..2) {
            val bean = IndicatorBean()
            for (j in 0..7) {
                bean.list.add("标题${j + 1}")
            }
            list.add(bean)
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