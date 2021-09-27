package com.hjc.module_other.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherItemCabinBinding
import com.hjc.module_other.http.entity.CabinBean

/**
 * @Author: HJC
 * @Date: 2021/9/26 16:35
 * @Description: 舱位列表
 */
class CabinAdapter(data: MutableList<CabinBean>) : BaseQuickAdapter<CabinBean, BaseViewHolder>(R.layout.other_item_cabin, data) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: CabinBean) {
        val binding = DataBindingUtil.getBinding<OtherItemCabinBinding>(holder.itemView)
        binding?.cabinBean = item
    }

}