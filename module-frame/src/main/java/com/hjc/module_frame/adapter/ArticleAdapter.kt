package com.hjc.module_frame.adapter

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameItemArticleBinding
import com.hjc.module_frame.http.entity.ArticleBean
import java.util.*

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 文章Adapter
 */
class ArticleAdapter(data: MutableList<ArticleBean>?) : BaseQuickAdapter<ArticleBean, BaseViewHolder>(
    R.layout.frame_item_article, data
) {

    override fun onItemViewHolderCreated(viewHolder: BaseViewHolder, viewType: Int) {
        DataBindingUtil.bind<ViewDataBinding>(viewHolder.itemView)
    }

    override fun convert(holder: BaseViewHolder, item: ArticleBean) {
        val binding = DataBindingUtil.getBinding<FrameItemArticleBinding>(holder.itemView)
        binding?.let {
            it.articleBean = item
        }
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        // 更换数据List的位置
        Collections.swap(data, fromPosition, toPosition)
        // 更换Adapter Item的视图位置
        notifyItemMoved(fromPosition, toPosition)
    }

}