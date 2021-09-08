package com.hjc.library_common.loadsir.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.library_common.R

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: ShimmerAdapter
 */
class ShimmerAdapter(data: MutableList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(
    R.layout.common_item_shimmer, data
) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: String) {

    }

}