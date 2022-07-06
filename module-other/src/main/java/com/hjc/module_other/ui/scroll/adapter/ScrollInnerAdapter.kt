package com.hjc.module_other.ui.scroll.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_other.R

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 内部Adapter
 */
class ScrollInnerAdapter(data: MutableList<String>? = null) : BaseQuickAdapter<String, BaseViewHolder>(
    R.layout.other_item_scroll_inner, data
) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_title, item)
    }

}