package com.hjc.base.utils.image;

import android.content.Context;
import android.widget.ImageView;

/**
 * @Author: HJC
 * @Date: 2019/3/21 17:44
 * @Description: Banner图片加载器
 */
public class GlideImageLoader extends com.youth.banner.loader.ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoader.loadImage(imageView, (String) path, 0);
    }
}
