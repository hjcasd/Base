package com.hjc.library_common.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:23
 * @Description: 自定义BindingAdapter(布局里直接使用)
 */
object CommonBindingAdapters {

    /**
     * 加载图片
     */
    @JvmStatic
    @BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
    fun loadImage(imageView: ImageView, url: String?, drawable: Drawable?) {
        if (!TextUtils.isEmpty(url)) {
            val requestOptions = RequestOptions()
                .placeholder(drawable)
                .error(drawable)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            Glide.with(imageView.context)
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView)
        }
    }

    /**
     * 加载背景图片
     */
    @JvmStatic
    @BindingAdapter("imageBgUrl")
    fun loadBgImage(viewGroup: ViewGroup, url: String?) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(viewGroup.context)
                .asBitmap()
                .load(url)
                .into(object : CustomTarget<Bitmap?>() {

                    override fun onLoadCleared(placeholder: Drawable?) {}

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap?>?
                    ) {
                        val drawable: Drawable = BitmapDrawable(viewGroup.resources, resource)
                        viewGroup.background = drawable
                    }
                })
        }
    }

    /**
     * 加载圆角图片
     */
    @JvmStatic
    @BindingAdapter(value = ["imageRoundUrl", "placeholder", "roundingRadius"], requireAll = false)
    fun loadRoundImage(
        imageView: ImageView,
        url: String?,
        drawable: Drawable?,
        roundingRadius: Int
    ) {
        if (!TextUtils.isEmpty(url)) {
            val requestOptions = RequestOptions()
                .placeholder(drawable)
                .error(drawable)
                .transform(CenterCrop(), RoundedCorners(roundingRadius))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            Glide.with(imageView.context)
                .load(url)
                .placeholder(drawable)
                .apply(requestOptions)
                .into(imageView)
        }
    }

    /**
     * 加载圆形图片
     */
    @JvmStatic
    @BindingAdapter(value = ["imageCircleUrl", "placeholder"], requireAll = false)
    fun loadCircleImage(imageView: ImageView, url: String?, drawable: Drawable?) {
        if (!TextUtils.isEmpty(url)) {
            val requestOptions = RequestOptions()
                .placeholder(drawable)
                .error(drawable)
                .transform(CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            Glide.with(imageView.context)
                .load(url)
                .apply(requestOptions)
                .into(imageView)
        }
    }

    /**
     * 加载高斯模糊图片
     */
    @JvmStatic
    @BindingAdapter(
        value = ["imageBlurUrl", "placeholder", "radius", "sampling"],
        requireAll = false
    )
    fun loadBlurImage(
        imageView: ImageView,
        url: String?,
        drawable: Drawable?,
        radius: Int,
        sampling: Int
    ) {
        val requestOptions = RequestOptions()
            .placeholder(drawable)
            .error(drawable)
            .transform(BlurTransformation(radius, sampling))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(imageView)
    }

}