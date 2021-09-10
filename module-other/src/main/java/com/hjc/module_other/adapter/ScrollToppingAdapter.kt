package com.hjc.module_other.adapter

import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_other.R
import com.hjc.module_other.http.entity.ScrollBean

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 滑动置顶Adapter
 */
class ScrollToppingAdapter : BaseDelegateMultiAdapter<ScrollBean, BaseViewHolder>() {

    init {
        setMultiTypeDelegate(ScrollMultiTypeDelegate())
    }

    override fun convert(holder: BaseViewHolder, item: ScrollBean) {
        holder.itemView.tag = item.type == ScrollBean.TYPE_TITLE
        holder.setText(R.id.tv_name, item.name)
    }

    // 代理类
    class ScrollMultiTypeDelegate : BaseMultiTypeDelegate<ScrollBean>() {

        override fun getItemType(data: List<ScrollBean>, position: Int): Int {
            val bean = data[position]
            return bean.itemType
        }

        init {
            // 绑定 item 类型
            addItemType(ScrollBean.TYPE_TITLE, R.layout.other_item_scroll_topping_title)
            addItemType(ScrollBean.TYPE_CONTENT, R.layout.other_item_scroll_topping_content)
        }
    }
}