package com.hjc.module_frame.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.library_net.model.ArticleBean
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameItemArticleBinding

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 文章Adapter
 */
class ArticleAdapter(data: MutableList<ArticleBean>?) : BaseQuickAdapter<ArticleBean, BaseViewHolder>(
    R.layout.frame_item_article, data) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        val binding = holder.getBinding<FrameItemArticleBinding>()
        if (binding != null) {
            binding.articleBean = item
        }
    }
}