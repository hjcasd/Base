package com.hjc.base.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import jp.wasabeef.glide.transformations.BlurTransformation;


/**
 * @Author: HJC
 * @Date: 2020/5/14 15:45
 * @Description: 自定义BindingAdapter
 */
public class CommonBindingAdapters {

    @BindingAdapter(value = {"imageUrl", "placeholder"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, Drawable drawable) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(drawable)
                    .error(drawable)
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade(500))
                    .into(imageView);
        }
    }

    @BindingAdapter("imageBgUrl")
    public static void loadBgImage(final ViewGroup viewGroup, String url) {
        if (!TextUtils.isEmpty(url)) {
            Glide.with(viewGroup.getContext())
                    .asBitmap()
                    .load(url)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(viewGroup.getResources(), resource);
                            viewGroup.setBackground(drawable);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });
        }
    }

    @BindingAdapter(value = {"imageRoundUrl", "placeholder", "roundingRadius"}, requireAll = false)
    public static void loadRoundImage(ImageView imageView, String url, Drawable drawable, int roundingRadius) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(drawable)
                    .error(drawable)
                    .transform(new RoundedCorners(roundingRadius))
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

            Glide.with(imageView.getContext())
                    .load(url)
                    .placeholder(drawable)
                    .apply(requestOptions)
                    .into(imageView);
        }
    }

    @BindingAdapter(value = {"imageCircleUrl", "placeholder"}, requireAll = false)
    public static void loadCircleImage(ImageView imageView, String url, Drawable drawable) {
        if (!TextUtils.isEmpty(url)) {
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(drawable)
                    .error(drawable)
                    .transform(new CircleCrop())
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

            Glide.with(imageView.getContext())
                    .load(url)
                    .apply(requestOptions)
                    .into(imageView);
        }
    }


    @BindingAdapter(value = {"imageBlurUrl", "placeholder", "radius", "sampling"}, requireAll = false)
    public static void loadBlurImage(ImageView imageView, String url, Drawable drawable, int radius, int sampling) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(drawable)
                .error(drawable)
                .transform(new BlurTransformation(radius, sampling))
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(imageView.getContext())
                .load(url)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imageView);
    }

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, boolean value) {
        view.setVisibility(value ? View.VISIBLE : View.GONE);
    }
}
