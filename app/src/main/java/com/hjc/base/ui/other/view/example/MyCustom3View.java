package com.hjc.base.ui.other.view.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.base.R;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:47
 * @Description: 图片文字
 */
public class MyCustom3View extends View {
    private Context mContext;

    private Picture mPicture = new Picture();

    private int mWidth;
    private int mHeight;

    private int mType = 0;

    public MyCustom3View(Context context) {
        super(context);
        mContext = context;
        recording();
    }

    public MyCustom3View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        recording();
    }

    /**
     * 录制内容，即将一些Canvas操作用Picture存储起来，录制的内容是不会直接显示在屏幕上的，只是存储起来了而已
     * 想要将Picture中的内容显示出来就需要手动调用播放(绘制),有如下3中方式绘制:
     * 1. 使用Picture提供的draw方法绘制。(不推荐使用)
     * 2. 使用Canvas提供的drawPicture方法绘制。
     * 3. 将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
     */
    private void recording() {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20f);

        // 开始录制
        Canvas canvas = mPicture.beginRecording(500, 500);
        canvas.translate(250, 250);
        canvas.drawCircle(0, 0, 100, paint);
        // 结束录制
        mPicture.endRecording();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mWidth = width;
        mHeight = height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画布颜色
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        switch (mType) {
            case 1:
                drawPicture(canvas);
                break;

            case 2:
                drawBitmap(canvas);
                break;

            case 3:
                drawText(canvas);
                break;

            default:
                break;
        }
    }

    /**
     * 矢量图
     */
    private void drawPicture(Canvas canvas) {
        // 将Picture中的内容绘制到canvas上
        canvas.drawPicture(mPicture, new RectF(0, 0, mPicture.getWidth(), mPicture.getHeight()));

//                PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
//                pictureDrawable.setBounds(0, 0, mPicture.getWidth(), mPicture.getHeight());
//                pictureDrawable.draw(canvas);
    }

    /**
     * 位图
     */
    private void drawBitmap(Canvas canvas) {
        // 将坐标系移动到画布中央
        canvas.translate(mWidth / 2, mHeight / 2);

        Bitmap bitmap = getBitmap();
//                canvas.drawBitmap(bitmap, new Matrix(), new Paint());

        // 指定图片绘制区域
        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        // 指定图片在屏幕上显示的区域
        Rect dst = new Rect(0, 0, 200, 200);
        canvas.drawBitmap(bitmap, src, dst, new Paint());
    }

    private Bitmap getBitmap() {
        Bitmap bitmap = null;
        try {
            InputStream is = mContext.getAssets().open("bitmap.png");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * 文字
     */
    private void drawText(Canvas canvas) {
        String text = "ABCDE";

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        paint.setAntiAlias(true);

        // 绘制文字
        canvas.drawText(text, 200, 200, paint);

        // 绘制截取的指定文字(BC)
//        canvas.drawText(text, 1, 3, 200, 200, paint);
    }

    public void draw(int type) {
        mType = type;
        invalidate();
    }
}
