package com.hjc.base.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.base.R;

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 画布操作
 */
public class MyCustom2View extends View {
    private Paint mPaint;

    private int mType = 0;
    private int mWidth;
    private int mHeight;

    public MyCustom2View(Context context) {
        super(context);
        initPaint();
    }

    public MyCustom2View(Context context, @Nullable AttributeSet attrs) {
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
                translate(canvas);
                break;

            case 2:
                scale(canvas);
                break;

            case 3:
                rotate(canvas);
                break;

            default:
                break;
        }
    }

    private void translate(Canvas canvas) {
        // 位移操作(位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动)
        // 将原点坐标由(0,0)移动到(200, 200)
        mPaint.setColor(Color.BLACK);
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);

        // 位移叠加
        mPaint.setColor(Color.BLUE);
        // 将原点坐标由(200,200)移动到(400, 400)
        canvas.translate(200, 200);
        canvas.drawCircle(0, 0, 100, mPaint);
    }

    private void scale(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        mPaint.setColor(Color.BLACK);
        RectF rectF = new RectF(0, -200, 200, 0);
        canvas.drawRect(rectF, mPaint);

        canvas.scale(0.5f, 0.5f);            //按原点进行缩放
//        canvas.scale(-0.5f, -0.5f);          //按原点进行缩放(如果为负数,则先缩放,然后按中心轴翻转)
//        canvas.scale(0.5f, 0.5f, 200, 0);    //缩放中心由原点变为(200, 0)进行缩放
//        canvas.scale(-0.5f, -0.5f, 200, 0);    //缩放中心由原点变为(200, 0)进行缩放,同时按中心轴翻转

//        canvas.scale(0.5f, 0.5f);   //缩放叠加
//        canvas.scale(0.5f, 0.5f);   // x轴实际缩放为0.5x0.5=0.25 y轴实际缩放为0.5x0.5=0.25

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
    }


    private void rotate(Canvas canvas) {
        canvas.translate(mWidth / 2, mHeight / 2);

        mPaint.setColor(Color.BLACK);
        RectF rectF = new RectF(0, -200, 200, 0);
        canvas.drawRect(rectF, mPaint);

//        canvas.rotate(180);        //旋转180,默认以原点旋转
//        canvas.rotate(180, 200, 0);  //以(200, 0)旋转180度

        canvas.rotate(90);  //旋转叠加
        canvas.rotate(90);  //旋转180

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(rectF, mPaint);
    }


    public void draw(int type) {
        mType = type;
        invalidate();
    }
}
