package com.hjc.library_common.loadsir.callback

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hjc.library_common.R
import com.hjc.library_common.adapter.ShimmerAdapter
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: 自定义 loadSir 骨架屏加载页面
 */
class CustomShimmerCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.common_layout_custom_shimmer
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return true
    }

    override fun onViewCreate(context: Context?, view: View?) {
        super.onViewCreate(context, view)
        val rvShimmer = view?.findViewById<RecyclerView>(R.id.rv_shimmer)
        val manager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvShimmer?.layoutManager = manager

        val list = mutableListOf("111", "222", "333", "444", "555")
        val adapter = ShimmerAdapter(list)
        rvShimmer?.adapter = adapter
    }
}