package com.hjc.base.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.chad.library.adapter.base.BaseQuickAdapter.AnimationType
import com.gyf.immersionbar.ImmersionBar
import com.hjc.base.R
import com.hjc.base.adapter.ArticleAdapter
import com.hjc.base.constant.RoutePath
import com.hjc.base.databinding.ActivityListBinding
import com.hjc.base.viewmodel.list.ListViewModel
import com.hjc.baselib.activity.BaseActivity
import com.hjc.baselib.widget.bar.OnViewLeftClickListener
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener

/**
 * @Author: HJC
 * @Date: 2021/1/8 14:00
 * @Description: 列表
 */
@Route(path = RoutePath.URL_LIST)
class ListActivity : BaseActivity<ActivityListBinding, ListViewModel>() {

    private lateinit var mAdapter: ArticleAdapter

    //页码
    private var mPage = 0

    override fun getLayoutId(): Int {
        return R.layout.activity_list
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
            .statusBarColor(R.color.app_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewModel?.loadArticleList(0, true)
    }

    override fun observeLiveData() {
        super.observeLiveData()

        mViewModel?.run {
            getListData().observe(this@ListActivity) { result ->
                if (mPage == 0) {
                    mAdapter.setNewInstance(result)
                } else {
                    mAdapter.addData(result)
                }
            }

            getRefreshData().observe(this@ListActivity) { result ->
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