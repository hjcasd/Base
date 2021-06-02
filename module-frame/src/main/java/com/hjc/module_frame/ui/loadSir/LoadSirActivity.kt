package com.hjc.module_frame.ui.loadSir

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter.AnimationType
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_frame.R
import com.hjc.module_frame.adapter.ArticleAdapter
import com.hjc.module_frame.databinding.FrameActivityLoadSirBinding
import com.hjc.module_frame.viewmodel.ListViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * @Author: HJC
 * @Date: 2021/1/8 14:00
 * @Description: 列表
 */
@Route(path = RoutePath.Frame.URL_LOAD_SIR)
class LoadSirActivity : BaseActivity<FrameActivityLoadSirBinding, ListViewModel>() {

    private lateinit var mAdapter: ArticleAdapter

    //页码
    private var mPage = 0

    override fun getLayoutId(): Int {
        return R.layout.frame_activity_load_sir
    }

    override fun createViewModel(): ListViewModel {
        return ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun initView() {
        super.initView()

        initLoadSir(mBindingView.smartRefreshLayout)

        val manager = LinearLayoutManager(this)
        mBindingView.rvList.layoutManager = manager

        mAdapter = ArticleAdapter(null)
        mBindingView.rvList.adapter = mAdapter

        mAdapter.setAnimationWithDefault(AnimationType.SlideInLeft)
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel?.loadArticleList(0, true)
    }

    override fun observeLiveData() {
        super.observeLiveData()

        mViewModel?.run {
            listData.observe(this@LoadSirActivity) { result ->
                if (mPage == 0) {
                    mAdapter.setNewInstance(result)
                } else {
                    mAdapter.addData(result)
                }
            }

            refreshData.observe(this@LoadSirActivity) { result ->
                if (result) {
                    mBindingView.smartRefreshLayout.finishRefresh()
                    mBindingView.smartRefreshLayout.finishLoadMore()
                }
            }
        }
    }

    override fun addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }
        })

        mBindingView.smartRefreshLayout.setOnRefreshLoadMoreListener(object :
            OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mPage++
                mViewModel?.loadArticleList(mPage, false)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mPage = 0
                mViewModel?.loadArticleList(mPage, false)
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }

    override fun onRetryBtnClick(v: View?) {
        super.onRetryBtnClick(v)
        mPage = 0
        mViewModel?.loadArticleList(mPage, true)
    }

}