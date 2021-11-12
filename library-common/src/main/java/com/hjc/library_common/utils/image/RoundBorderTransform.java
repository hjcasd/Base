package com.hjc.library_common.utils.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.security.MessageDigest;

/**
 * @Author: HJC
 * @Date: 2021/11/12 9:33
 * @Description: 带圆角边框的Transform
 */
public class RoundBorderTransform extends CenterCrop {

    private final float mRadius;
    private float mBorderWidth = 0f;
    private int mBorderColor = 0;

    /**
     * 圆角
     *
     * @param radius 圆角大小
     */
    public RoundBorderTransform(int radius) {
        mRadius = Resources.getSystem().getDisplayMetrics().density * radius;
    }

    /**
     * 圆角带边框
     *
     * @param radius      圆角大小
     * @param borderColor 边框颜色
     * @param borderWidth 边框宽度
     */
    public RoundBorderTransform(int radius, int borderColor, int borderWidth) {
        mRadius = Resources.getSystem().getDisplayMetrics().density * radius;
        mBorderColor = borderColor;
        mBorderWidth = Resources.getSystem().getDisplayMetrics().density * borderWidth;
    }

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
        Bitmap transform = super.transform(pool, toTransform, outWidth, outHeight);
        return roundCrop(pool, transform);
    }

    private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
        if (source == null) {
            return null;
        }

        Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);

        if (mBorderColor != 0) {
            // 绘制圆角带边框
            Paint borderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            borderPaint.setColor(mBorderColor);
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setStrokeWidth(mBorderWidth);
            drawRoundRect(canvas, source.getWidth(), source.getHeight(), paint, borderPaint);
        } else {
            // 绘制圆角
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, mRadius, mRadius, paint);
        }

        return result;
    }

    /**
     * 绘制边框
     */
    private void drawRoundRect(Canvas canvas, float width, float height, Paint paint, Paint borderPaint) {
        float halfBorder = mBorderWidth / 2;
        Path path = new Path();
        float[] pos = new float[8];
        int shift = 0b1111;
        int index = 3;
        while (index >= 0) {
            // 设置四个边角的弧度半径
            pos[2 * index + 1] = ((shift & 1) > 0) ? mRadius : 0;
            pos[2 * index] = ((shift & 1) > 0) ? mRadius : 0;
            shift = shift >> 1;
            index--;
        }
        path.addRoundRect(new RectF(halfBorder, halfBorder, width - halfBorder, height - halfBorder), pos, Path.Direction.CW);
        // 绘制要加载的图形
        canvas.drawPath(path, paint);
        // 绘制边框
        canvas.drawPath(path, borderPaint);
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

}
