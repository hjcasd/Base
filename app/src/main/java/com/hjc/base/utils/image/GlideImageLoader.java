package com.hjc.base.utils.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * Banner图片加载器
 */
public class GlideImageLoader extends com.youth.banner.loader.ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoader.loadImage(context.getApplicationContext(), (String) path, imageView);
    }
}
