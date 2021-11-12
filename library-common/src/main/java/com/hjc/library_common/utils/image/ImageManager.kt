package com.hjc.library_common.utils.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.hjc.library_base.R
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

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
            .dontAnimate()

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    /**
     * 加载带圆角的图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param radius    圆角大小
     * @param cornerType 圆角类型
     */
    fun loadRoundImage(
        imageView: ImageView,
        url: String,
        radius: Int,
        cornerType: RoundedCornersTransformation.CornerType = RoundedCornersTransformation.CornerType.ALL,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .transform(RoundedCornersTransformation(radius, 0, cornerType))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }

    /**
     * 加载带边框和圆角的图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param radius 圆角大小
     * @param borderWidth 边框宽度
     * @param borderColor 边框颜色
     */
    fun loadBorderImage(
        imageView: ImageView,
        url: String,
        radius: Int,
        borderWidth: Int,
        borderColor: Int,
        placeholderDrawable: Int = R.mipmap.base_img_default,
        errorDrawable: Int = R.mipmap.base_img_default
    ) {
        val requestOptions = RequestOptions()
            .placeholder(placeholderDrawable)
            .error(errorDrawable)
            .transform(RoundBorderTransform(radius, borderColor, borderWidth))
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .dontAnimate()

        Glide.with(imageView.context)
            .load(url)
            .apply(requestOptions)
            .into(imageView)
    }


    /**
     * 加载指定大小的图片
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
     * 加载高斯模糊的图片
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
