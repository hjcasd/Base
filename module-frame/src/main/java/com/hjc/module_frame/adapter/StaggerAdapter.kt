package com.hjc.module_frame.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.hjc.module_frame.R
import com.hjc.module_frame.http.entity.StaggerBean


/**
 * @Author: HJC
 * @Date: 2021/1/8 16:22
 * @Description: 瀑布流Adapter
 */
class StaggerAdapter(data: MutableList<StaggerBean>?) : BaseQuickAdapter<StaggerBean, BaseViewHolder>(
    R.layout.frame_item_stagger, data
) {

    override fun convert(holder: BaseViewHolder, item: StaggerBean) {
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_sub_title, item.subTitle)

        val ivCover = holder.getView<ImageView>(R.id.iv_cover)

        val requestOptions = RequestOptions()
            .placeholder(R.mipmap.common_img_default)
            .error(R.mipmap.common_img_default)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()

        val width = item.width
        val height = item.height
        if (height > 0 && width > 0) {
//            val layoutParams = ivCover.layoutParams as LinearLayout.LayoutParams
//            layoutParams.width = width
//            layoutParams.height = height
//            ivCover.layoutParams = layoutParams

            Glide.with(context)
                .load(item.url)
                .override(width, height)
                .apply(requestOptions)
                .into(ivCover)
        } else {
            Glide.with(context)
                .asBitmap()
                .load(item.url)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        // 图片实际宽高
                        val actualWidth = resource.width
                        val actualHeight = resource.height
                        LogUtils.e("实际宽度和高度 : $actualWidth = $actualHeight")

                        // 宽高比
                        val ratio: Float = actualWidth * 1f / actualHeight

                        // 显示宽高
                        val showWidth = ScreenUtils.getScreenWidth() / 2
                        val showHeight = (showWidth / ratio).toInt()
                        LogUtils.e("显示宽度和高度 : $showWidth = $showHeight")

                        item.width = showWidth
                        item.height = showHeight

//                        val layoutParams = ivCover.layoutParams as LinearLayout.LayoutParams
//                        layoutParams.width = showWidth
//                        layoutParams.height = showHeight
//                        ivCover.layoutParams = layoutParams

                        Glide.with(context)
                            .load(item.url)
                            .override(showWidth, showHeight)
                            .apply(requestOptions)
                            .into(ivCover)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })
        }
    }

}