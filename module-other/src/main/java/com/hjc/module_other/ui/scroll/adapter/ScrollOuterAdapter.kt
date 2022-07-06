package com.hjc.module_other.ui.scroll.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_other.R
import com.hjc.module_other.http.entity.IndicatorBean

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 外部Adapter
 */
class ScrollOuterAdapter(data: MutableList<IndicatorBean>? = null) : BaseQuickAdapter<IndicatorBean, BaseViewHolder>(
    R.layout.other_item_scroll_outer, data
) {

    override fun convert(holder: BaseViewHolder, item: IndicatorBean) {
        val rvList = holder.getView<RecyclerView>(R.id.rv_list)

        val manager = GridLayoutManager(context, 4)
        rvList.layoutManager = manager

        val adapter = ScrollInnerAdapter(item.list)
        rvList.adapter = adapter
    }

}