package com.hjc.base.ui.other.view.example;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class MyCustom1View extends View {
    private Paint mPaint;

    private int mType = 1;

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
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mType) {
            case 1:
                // 画一个点
                canvas.drawPoint(100, 100, mPaint);
                break;

            case 2:
                // 画多个点
                canvas.drawPoints(new float[]{200, 200, 300, 300, 400, 400}, mPaint);
                break;

            case 3:
                // 画一条直线
                canvas.drawLine(200, 200, 400, 400, mPaint);
                break;

            case 4:
                // 画多条直线
                canvas.drawLines(new float[]{500, 500, 700, 500, 600, 600, 800, 600}, mPaint);
                break;

            case 5:
                // 画矩形
                canvas.drawRect(100, 100, 400, 400, mPaint);
                break;

            case 6:
                // 画圆角矩形
                RectF rectF1 = new RectF(100, 100, 400, 300);
                canvas.drawRoundRect(rectF1, 30, 30, mPaint);
                break;

            case 7:
                // 画椭圆
                RectF rectF2 = new RectF(100, 100, 400, 300);  //绘制
                canvas.drawOval(rectF2, mPaint);
                break;

            case 8:
                // 画圆
                canvas.drawCircle(500, 500, 200, mPaint);  //画圆
                break;

            case 9:
                // 画圆弧, 第四个参数为是否使用中心点
                RectF rectF3 = new RectF(100, 100, 400, 300);
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
