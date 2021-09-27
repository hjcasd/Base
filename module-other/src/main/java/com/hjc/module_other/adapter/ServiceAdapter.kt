package com.hjc.module_other.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherItemCabinBinding
import com.hjc.module_other.databinding.OtherItemServiceBinding

/**
 * @Author: HJC
 * @Date: 2021/9/26 16:35
 * @Description: 服务列表
 */
class ServiceAdapter(data: MutableList<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.other_item_service, data) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: String) {
        val binding = DataBindingUtil.getBinding<OtherItemServiceBinding>(holder.itemView)
        binding?.title = item
    }

}