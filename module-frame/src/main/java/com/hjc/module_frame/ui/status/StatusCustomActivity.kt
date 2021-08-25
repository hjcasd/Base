package com.hjc.module_frame.ui.status

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.base.IStatusView
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.loadsir.CustomStatusViewImpl
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_frame.R
import com.hjc.module_frame.adapter.ArticleAdapter
import com.hjc.module_frame.databinding.FrameActivityStatusCustomBinding
import com.hjc.module_frame.viewmodel.StatusCustomViewModel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author: HJC
 * @Date: 2021/1/8 14:00
 * @Description: 列表
 */
@Route(path = RouteFramePath.URL_STATUS_CUSTOM)
class StatusCustomActivity : BaseActivity<FrameActivityStatusCustomBinding, StatusCustomViewModel>() {

    private lateinit var mAdapter: ArticleAdapter

    //页码
    private var mPage = 0

    override fun getLayoutId(): Int {
        return R.layout.frame_activity_status_custom
    }

    override fun createViewModel(): StatusCustomViewModel {
        return ViewModelProvider(this)[StatusCustomViewModel::class.java]
    }

    override fun initView() {
        super.initView()

        initLoadSir(mBindingView.smartRefreshLayout)

        val manager = LinearLayoutManager(this)
        mBindingView.rvList.layoutManager = manager

        mAdapter = ArticleAdapter(null)
        mBindingView.rvList.adapter = mAdapter

        mAdapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.SlideInLeft)
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
    }

    override fun createStatusView(): IStatusView {
        return CustomStatusViewImpl()
    }

    override fun initData(savedInstanceState: Bundle?) {
        EventManager.register(this)
        mViewModel?.loadArticleList(0, true)
    }

    override fun observeLiveData() {
        super.observeLiveData()

        mViewModel?.run {
            listData.observe(this@StatusCustomActivity) { result ->
                if (mPage == 0) {
                    mAdapter.setNewInstance(result)
                } else {
                    mAdapter.addData(result)
                }
            }

            refreshData.observe(this@StatusCustomActivity) { result ->
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

        mAdapter.setOnItemClickListener { _, _, position ->
            ToastUtils.showShort("position: $position")
        }
    }

    override fun onSingleClick(v: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        if (event.code === EventCode.LOAD_SIR_RETRY) {
            mPage = 0
            mViewModel?.loadArticleList(mPage, true)
        }
    }

}