package com.hjc.module_senior.widget.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.module_senior.widget.anim.helper.Point;
import com.hjc.module_senior.widget.anim.helper.PointEvaluator;

/**
 * @Author: HJC
 * @Date: 2021/8/15 15:41
 * @Description: 自定义补间动画View
 */
public class ValueAnimView extends View {

    private static final float RADIUS = 50f;
    private Point mCurrentPoint;
    private Paint mPaint;

    public ValueAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mCurrentPoint == null) {
            mCurrentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas) {
        float x = mCurrentPoint.getX();
        float y = mCurrentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(RADIUS, RADIUS);
        Point endPoint = new Point(getWidth() - RADIUS, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(animation -> {
            mCurrentPoint = (Point) animation.getAnimatedValue();
            invalidate();
        });
        anim.setDuration(5000);
        anim.start();
    }
}
