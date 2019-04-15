package com.hjc.base.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.youth.banner.loader.ImageLoader;

/**
 * @Author: HJC
 * @Date: 2019/3/21 17:44
 * @Description: Banner图片加载器
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageManager.loadImage(imageView, (String) path, 0);
    }
}
