package com.hjc.base.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.hjc.base.R;

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:47
 * @Description: Path操作
 */
public class MyCustom4View extends View {
    private Paint mPaint;
    private Paint mAlisPaint;

    private int mWidth;
    private int mHeight;

    private int mType = 0;

    public MyCustom4View(Context context) {
        super(context);
        initPaint();
    }

    public MyCustom4View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        // 画笔颜色
        mPaint.setColor(Color.BLACK);
        // 画笔填充模式
        mPaint.setStyle(Paint.Style.STROKE);
        // 描边宽度
        mPaint.setStrokeWidth(4f);
        // 抗锯齿
        mPaint.setAntiAlias(true);

        mAlisPaint = new Paint();
        mAlisPaint.setColor(Color.WHITE);
        mAlisPaint.setStyle(Paint.Style.FILL);
        mAlisPaint.setStrokeWidth(4f);
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
        canvas.drawColor(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
        canvas.translate(mWidth / 2, mHeight / 2);
        float[] points = {-mWidth, 0, mWidth, 0, 0, -mHeight, 0, mHeight};
        canvas.drawLines(points, mAlisPaint);

        switch (mType) {
            case 1:
                drawView1(canvas);
                break;

            case 2:
                drawView2(canvas);
                break;

            case 3:
                drawView3(canvas);
                break;

            case 4:
                drawView4(canvas);
                break;

            default:
                break;
        }
    }

    /**
     * lineTo(), moveTo(), setLastPoint(), close()
     */
    private void drawView1(Canvas canvas) {
        Path path = new Path();
        // 从上一次点(默认原点)到指定点间画一条直线
        path.lineTo(200, 200);

        // 移动下一次操作的起点位置(将起点移动到(200, 100))
//        path.moveTo(200, 100);

        // 重置上一次操作的最后一个点(将最后一个点由(200, 200)变为(200, 100))
//        path.setLastPoint(200, 100);

        path.lineTo(200, 0);

        // 连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形
        path.close();

        canvas.drawPath(path, mPaint);
    }

    /**
     * 基本形状
     */
    private void drawView2(Canvas canvas) {
        Path path = new Path();
        // 添加矩形,cw表示顺时针,ccw表示逆时针
        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        path.setLastPoint(-300, 300);

        canvas.drawPath(path, mPaint);
    }


    /**
     * Path
     */
    private void drawView3(Canvas canvas) {
        // 翻转y坐标轴
        canvas.scale(1, -1);

        Path path = new Path();
        Path src = new Path();

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);

        canvas.drawPath(path, mPaint);
    }


    /**
     * addArc(), arcTo
     */
    private void drawView4(Canvas canvas) {
        canvas.scale(1, -1);

        Path path = new Path();

        path.lineTo(100, 100);

        RectF oval = new RectF(0, 0, 300, 300);
        // 直接添加一个圆弧到path中
//        path.addArc(oval, 0, 270);

        // 添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
        path.arcTo(oval, 0, 270);

        canvas.drawPath(path, mPaint);
    }

    public void draw(int type) {
        mType = type;
        invalidate();
    }
}
