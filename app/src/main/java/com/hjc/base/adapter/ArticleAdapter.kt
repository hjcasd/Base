package com.hjc.base.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.base.R
import com.hjc.base.databinding.ItemArticleBinding
import com.hjc.base.model.ArticleBean

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 文章Adapter
 */
class ArticleAdapter(data: MutableList<ArticleBean>?) : BaseQuickAdapter<ArticleBean, BaseViewHolder>(R.layout.item_article, data) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        val binding = holder.getBinding<ItemArticleBinding>()
        if (binding != null) {
            binding.articleBean = item
        }
    }
}