package com.hjc.module_senior.widget.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.module_senior.widget.anim.helper.DecelerateAccelerateInterpolator;
import com.hjc.module_senior.widget.anim.helper.Point;
import com.hjc.module_senior.widget.anim.helper.PointEvaluator;


public class MyAnimView3 extends View {
    public static final float RADIUS = 50f;
    private Point mCurrentPoint;
    private Paint mPaint;

    public MyAnimView3(Context context, AttributeSet attrs) {
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
        Point startPoint = new Point(getWidth() / 2, RADIUS);
        Point endPoint = new Point(getWidth() / 2, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        //设置加速的插值器
//        anim.setInterpolator(new AccelerateInterpolator());
        //设置回弹的插值器
//        anim.setInterpolator(new BounceInterpolator());
        anim.setInterpolator(new DecelerateAccelerateInterpolator());
        anim.setDuration(3000);
        anim.start();
    }
}
