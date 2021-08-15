package com.hjc.module_senior.widget.anim;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.hjc.module_senior.widget.anim.helper.DecAccInterpolator;
import com.hjc.module_senior.widget.anim.helper.Point;
import com.hjc.module_senior.widget.anim.helper.PointEvaluator;

/**
 * @Author: HJC
 * @Date: 2021/8/15 15:40
 * @Description: 自定义插值动画view
 */
public class InterpolatorAnimView extends View {

    private static final float RADIUS = 50f;
    private Point mCurrentPoint;
    private Paint mPaint;

    public InterpolatorAnimView(Context context, AttributeSet attrs) {
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
        Point startPoint = new Point(getWidth() / 2f, RADIUS);
        Point endPoint = new Point(getWidth() / 2f, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(animation -> {
            mCurrentPoint = (Point) animation.getAnimatedValue();
            invalidate();
        });
        //设置加速的插值器
//        anim.setInterpolator(new AccelerateInterpolator());
        //设置回弹的插值器
//        anim.setInterpolator(new BounceInterpolator());
        anim.setInterpolator(new DecAccInterpolator());
        anim.setDuration(3000);
        anim.start();
    }
}
