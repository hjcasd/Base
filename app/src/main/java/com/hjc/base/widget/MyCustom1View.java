package com.hjc.base.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hjc.base.R;

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 基本图形
 */
public class MyCustom1View extends View {
    private Paint mPaint;
    private Paint mAlisPaint;

    private int mType = 0;

    public MyCustom1View(Context context) {
        super(context);
        initPaint();
    }

    public MyCustom1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        // 画笔颜色
        mPaint.setColor(Color.BLACK);
        // 画笔填充模式
        mPaint.setStyle(Paint.Style.FILL);
        // 描边宽度
        mPaint.setStrokeWidth(20f);
        // 抗锯齿
        mPaint.setAntiAlias(true);

        mAlisPaint = new Paint();
        mAlisPaint.setColor(Color.BLUE);
        mAlisPaint.setStyle(Paint.Style.FILL);
        mAlisPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 设置画布颜色
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));

        // 画X轴
        canvas.drawLine(0, 0, getWidth(), 0, mAlisPaint);
        // 画Y轴
        canvas.drawLine(0, 0, 0, getHeight(), mAlisPaint);

        switch (mType) {
            case 1:
                // 画一个点: (100, 100)
                canvas.drawPoint(100, 100, mPaint);
                break;

            case 2:
                // 画多个点: (200, 200),(300, 300),(400, 400)
                float[] points1 = {200, 200, 300, 300, 400, 400};
                canvas.drawPoints(points1, mPaint);
                break;

            case 3:
                // 画一条直线: (200, 200) -> (400, 400)
                canvas.drawLine(200, 200, 400, 400, mPaint);
                break;

            case 4:
                // 画多条直线: (100, 100) -> (200, 200), (100, 300) -> (200, 400)
                float[] points2 = {100, 100, 200, 200, 100, 300, 200, 400};
                canvas.drawLines(points2, mPaint);
                break;

            case 5:
                // 画矩形: 左上(100, 100), 右下(400, 300)
                canvas.drawRect(100, 100, 400, 300, mPaint);
                break;

            case 6:
                // 画圆角矩形: 左上(100, 100), 右下(400, 300)
                RectF rectF1 = new RectF(100, 100, 400, 300);
                // rx, ry为椭圆的两个半径(一般一样大小)
                canvas.drawRoundRect(rectF1, 30, 30, mPaint);
                break;

            case 7:
                // 画椭圆: 左上(100, 100), 右下(400, 300)矩形的内切图形
                RectF rectF2 = new RectF(100, 100, 400, 300);
                canvas.drawOval(rectF2, mPaint);
                break;

            case 8:
                // 画圆: 圆心(200, 200),半径150
                canvas.drawCircle(200, 200, 150, mPaint);
                break;

            case 9:
                // 画圆弧: 左上(100, 100), 右下(400, 400)的矩形中画弧, 角度为90, 使用中心点
                RectF rectF3 = new RectF(100, 100, 400, 400);
                canvas.drawArc(rectF3, 0, 90, true, mPaint);
//                canvas.drawArc(rectF3, 0, 90, false, mPaint);
                break;

            default:
                break;
        }
    }


    public void draw(int type) {
        mType = type;
        invalidate();
    }
}
