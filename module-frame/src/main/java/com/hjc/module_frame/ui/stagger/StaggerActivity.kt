package com.hjc.module_frame.ui.stagger

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ConvertUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.library_widget.list.decoration.StaggerItemDecoration
import com.hjc.module_frame.R
import com.hjc.module_frame.adapter.StaggerAdapter
import com.hjc.module_frame.databinding.FrameActivityStaggerBinding
import com.hjc.module_frame.http.entity.StaggerBean

/**
 * @Author: HJC
 * @Date: 2021/1/8 14:00
 * @Description: 状态布局(LoadSir)
 */
@Route(path = RouteFramePath.URL_STAGGER)
class StaggerActivity : BaseActivity<FrameActivityStaggerBinding, CommonViewModel>() {

    private lateinit var mAdapter: StaggerAdapter

    private lateinit var layoutManager :StaggeredGridLayoutManager

    override fun getLayoutId(): Int {
        return R.layout.frame_activity_stagger
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE
        mBindingView.rvList.layoutManager = layoutManager

        mBindingView.rvList.addItemDecoration(StaggerItemDecoration(ConvertUtils.dp2px(10f)))

        mAdapter = StaggerAdapter(generateData())
        mBindingView.rvList.adapter = mAdapter
    }

    private fun generateData(): MutableList<StaggerBean> {
        val dataList = mutableListOf<StaggerBean>()
        dataList.add(StaggerBean("http://img04.sogoucdn.com/app/a/100520021/3a9518af4cb8d9f991eb06b43f9545d9", "Xiaomi MIX 4小米最新上市旗舰手机", "最高可用5000积分"))
        dataList.add(StaggerBean("https://i04piccdn.sogoucdn.com/9bc3889c2b531d90","小米 MI 儿童电话手表", "最高可用560积分"))
        dataList.add(StaggerBean("https://img03.sogoucdn.com/app/a/100520021/cfe7b8ab22edd09245233a3313f223c9","Apple AirPods 第三代", "最高可用54353积分"))
        dataList.add(StaggerBean("https://i02piccdn.sogoucdn.com/3f51b383c8417da0","AirPods Pro耳机保护套软壳保护套", "最高可用50010积分"))
        dataList.add(StaggerBean("http://img04.sogoucdn.com/app/a/100520021/1b5c563dec58422fa58a1abb80f6d255","稻田迷你家用小蒸锅", "最高可用50020积分"))
        dataList.add(StaggerBean("http://img01.sogoucdn.com/app/a/100520021/f14cb2be1c2df8a16849559b799fa263", "Xiaomi MIX 4小米最新上市旗舰手机", "最高可用502100积分"))
        dataList.add(StaggerBean("https://i02piccdn.sogoucdn.com/3ec3e4f51bdeaa68", "AirPods Pro耳机保护套软壳保护套", "最高可用5232积分"))
        dataList.add(StaggerBean("https://i03piccdn.sogoucdn.com/04d3878a5e65ad38","稻田迷你家用小蒸锅", "最高可用123积分"))
        dataList.add(StaggerBean("https://i03piccdn.sogoucdn.com/dcabd504fc76c3c6", "小米 MI 儿童电话手表", "最高可用343433积分"))
        dataList.add(StaggerBean("https://i02piccdn.sogoucdn.com/8b477d431d1d08c4", "AirPods Pro耳机保护套软壳保护套", "最高可用232积分"))
        dataList.add(StaggerBean("https://i03piccdn.sogoucdn.com/353caf937824594b", "Xiaomi MIX 4小米最新上市旗舰手机", "最高可用2121分"))
        dataList.add(StaggerBean("https://i03piccdn.sogoucdn.com/3ddab79aa4b7079c", "稻田迷你家用小蒸锅", "最高可用12120积分"))
        return dataList
    }

    override fun addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })

//        mBindingView.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                val first = IntArray(2)
//                layoutManager.findFirstCompletelyVisibleItemPositions(first)
//                if (newState === RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
//                    layoutManager.invalidateSpanAssignments()
//                }
//            }
//        })
    }

    override fun onSingleClick(v: View?) {

    }

}