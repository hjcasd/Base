package com.hjc.module_other.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherItemScrollIndicatorBinding

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 文章Adapter
 */
class ScrollLineAdapter(data: MutableList<String>? = null) : BaseQuickAdapter<String, BaseViewHolder>(
    R.layout.other_item_scroll_indicator, data
) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = DataBindingUtil.getBinding<OtherItemScrollIndicatorBinding>(holder.itemView)
        binding?.title = item
    }

}