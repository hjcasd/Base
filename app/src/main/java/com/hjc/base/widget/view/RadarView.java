package com.hjc.base.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Author: HJC
 * @Date: 2019/11/1 14:42
 * @Description: 自定义雷达图
 */
public class RadarView extends View {

    // 数据个数
    private int count = 6;

    // 角度
    private float angle = (float) (Math.PI * 2 / count);

    // 网格最大半径
    private float radius;

    // 中心点X,Y坐标
    private int centerX;
    private int centerY;

    // 各维度标题
    private final String[] titles = {"a", "b", "c", "d", "e", "f"};
    // 各维度数值
    private final double[] data = {100, 80, 60, 80, 100, 60};
    // 数据最大值
    private final float maxValue = 100;

    // 雷达区画笔
    private Paint mainPaint;
    // 数据区画笔
    private Paint valuePaint;
    // 文本画笔
    private Paint textPaint;


    public RadarView(Context context) {
        super(context);
        initPaint();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public RadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mainPaint = new Paint();
        mainPaint.setStyle(Paint.Style.STROKE);
        mainPaint.setStrokeWidth(3f);
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(Color.GRAY);

        valuePaint = new Paint();
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setColor(Color.BLUE);
        valuePaint.setAntiAlias(true);

        textPaint = new Paint();
        textPaint.setTextSize(40f);
        textPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (Math.min(w, h) >> 1) * 0.8f;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        drawPolygon(canvas);
        drawLines(canvas);
        drawText(canvas);
        drawRegion(canvas);
    }

    /**
     * 绘制多边形
     *
     * @param canvas 画布
     */
    private void drawPolygon(Canvas canvas) {
        Path path = new Path();

        // 蜘蛛丝之间的间距
        float r = radius / (count - 1);
        for (int i = 1; i < count; i++) {
            // 当前半径
            float curR = r * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制直线
     *
     * @param canvas 画布
     */
    private void drawLines(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            path.moveTo(centerX, centerY);
            float x = (float) (centerX + radius * Math.cos(angle * i));
            float y = (float) (centerY + radius * Math.sin(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, mainPaint);
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for (int i = 0; i < count; i++) {
            float x = (float) (centerX + (radius + fontHeight / 2) * Math.cos(angle * i));
            float y = (float) (centerY + (radius + fontHeight / 2) * Math.sin(angle * i));

            if (angle * i >= 0 && angle * i <= Math.PI / 2) {
                // 第4象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {
                // 第3象限
                canvas.drawText(titles[i], x, y, textPaint);
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {
                // 第2象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {
                // 第1象限
                float dis = textPaint.measureText(titles[i]);
                canvas.drawText(titles[i], x - dis, y, textPaint);
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas 画布
     */
    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        valuePaint.setAlpha(255);
        for (int i = 0; i < count; i++) {
            double percent = data[i] / maxValue;
            float x = (float) (centerX + radius * Math.cos(angle * i) * percent);
            float y = (float) (centerY + radius * Math.sin(angle * i) * percent);
            if (i == 0) {
                path.moveTo(x, centerY);
            } else {
                path.lineTo(x, y);
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10, valuePaint);
        }
        valuePaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, valuePaint);
        valuePaint.setAlpha(127);
        //绘制填充区域
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }
}
