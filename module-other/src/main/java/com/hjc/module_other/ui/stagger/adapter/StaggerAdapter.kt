package com.hjc.module_other.ui.stagger.adapter

import android.widget.ImageView
import android.widget.LinearLayout
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.library_common.utils.image.ImageManager
import com.hjc.module_other.R
import com.hjc.module_other.http.entity.StaggerBean


/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 瀑布流Adapter
 */
class StaggerAdapter(data: MutableList<StaggerBean>?) : BaseQuickAdapter<StaggerBean, BaseViewHolder>(
    R.layout.other_item_stagger, data
) {

    override fun convert(holder: BaseViewHolder, item: StaggerBean) {
        val ivCover = holder.getView<ImageView>(R.id.iv_cover)

        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_sub_title, item.subTitle)

        val width = item.width
        val height = item.height
        if (height > 0 && width > 0) {
            // 保存的宽高
            val layoutParams = ivCover.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = width
            layoutParams.height = height
            ivCover.layoutParams = layoutParams
        } else {
            // 显示的宽高
            val ratio = item.ratio
            val showWidth = ScreenUtils.getScreenWidth() / 2
            val showHeight = (showWidth / ratio).toInt()
            LogUtils.e("显示宽度和高度 : $showWidth = $showHeight")

            item.width = showWidth
            item.height = showHeight

            val layoutParams = ivCover.layoutParams as LinearLayout.LayoutParams
            layoutParams.width = showWidth
            layoutParams.height = showHeight
            ivCover.layoutParams = layoutParams
        }

        ImageManager.loadImage(ivCover, item.url)
    }

}