package com.hjc.base.utils.image;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.hjc.base.R;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * @Author: HJC
 * @Date: 2019/3/21 17:44
 * @Description: Glide封装类
 */
public class ImageManager {

    /**
     * 加载图片(默认方式)
     *
     * @param imageView 控件id
     * @param url       图片地址
     */
    public static void loadImage(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDefaultPic(1))
                .error(getDefaultPic(0))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }


    /**
     * 加载圆形图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     */
    public static void loadCircleImage(ImageView imageView, String url) {
        RequestOptions requestOptions = new RequestOptions()
                .error(getDefaultPic(0))
                .transform(new CircleCrop())
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param radius    圆角大小
     */
    public static void loadRoundImage(ImageView imageView, String url, int radius) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDefaultPic(1))
                .error(getDefaultPic(0))
                .transform(new CenterCrop(), new RoundedCorners(radius))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }

    /**
     * 加载图片指定大小
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param width     控件宽度
     * @param height    控件高度
     */
    public static void loadSizeImage(ImageView imageView, String url, int width, int height) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDefaultPic(1))
                .error(getDefaultPic(0))
                .override(width, height)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }

    /**
     * 加载高斯模糊图片
     *
     * @param imageView 控件id
     * @param url       图片地址
     * @param radius    模糊级数 最大25
     * @param sampling  采样率
     */
    public static void loadBlurImage(ImageView imageView, String url, int radius, int sampling) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(getDefaultPic(1))
                .error(getDefaultPic(0))
                .transform(new BlurTransformation(radius, sampling))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }

    /**
     * 设置默认图片
     *
     * @param type 图片类型
     * @return 图片id
     */
    private static int getDefaultPic(int type) {
        switch (type) {
            case 0:
                return R.mipmap.ic_launcher;

            case 1:
                return R.mipmap.icon_default_pic;

            default:
                return R.mipmap.ic_launcher;
        }
    }
}
