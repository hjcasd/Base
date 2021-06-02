package com.hjc.library_common.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hjc.library_base.R
import jp.wasabeef.glide.transformations.BlurTransformation

/**
 * @Author: HJC
 * @Date: 2019/3/21 17:44
 * @Description: Glide封装类
 */
object ImageManager {

    /**
     * 加载图片(默认方式)
     *
     * @param imageView 控件id
     * @param url       图片地址
     */
    fun loadImage(
        imageView: ImageView,
        url: String,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(imageView)
    }


    /**
     * 加载圆形图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     */
    fun loadCircleImage(
        imageView: ImageView,
        url: String,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .transform(CircleCrop())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(imageView)
    }

    /**
     * 加载圆角图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param radius    圆角大小
     */
    fun loadRoundImage(
        imageView: ImageView,
        url: String,
        radius: Int,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .transform(CenterCrop(), RoundedCorners(radius))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(imageView)
    }

    /**
     * 加载图片指定大小
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param width     控件宽度
     * @param height    控件高度
     */
    fun loadSizeImage(
        imageView: ImageView,
        url: String,
        width: Int,
        height: Int,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .override(width, height)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(imageView)
    }

    /**
     * 加载高斯模糊图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param radius    模糊级数 最大25
     * @param sampling  采样率
     */
    fun loadBlurImage(
        imageView: ImageView,
        url: String,
        radius: Int,
        sampling: Int,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .transform(BlurTransformation(radius, sampling))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .transition(DrawableTransitionOptions.withCrossFade(500))
            .into(imageView)
    }

}
