package com.hjc.module_senior.ui.animation.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.module_senior.ui.animation.helper.ColorEvaluator;
import com.hjc.module_senior.ui.animation.helper.Point;
import com.hjc.module_senior.ui.animation.helper.PointEvaluator;

/**
 * @Author: HJC
 * @Date: 2021/8/15 15:40
 * @Description: 自定义ObjectAnimator动画View
 */
public class ObjectAnimView extends View {

    private static final float RADIUS = 50f;
    private Point mCurrentPoint;
    private Paint mPaint;
    private String color;

    public ObjectAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
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
        ValueAnimator anim1 = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim1.addUpdateListener(animation -> {
            mCurrentPoint = (Point) animation.getAnimatedValue();
            invalidate();
        });

        ObjectAnimator anim2 = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2);
        animSet.setDuration(5000);
        animSet.start();
    }
}
