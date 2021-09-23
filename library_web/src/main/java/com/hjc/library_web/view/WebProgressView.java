package com.hjc.library_web.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

/**
 * @Author: HJC
 * @Date: 2021/1/14 14:35
 * @Description: WebView平滑进度条
 */
public class WebProgressView extends FrameLayout {

    private final Context mContext;

    /**
     * 默认匀速动画最大的时长
     */
    public static final int MAX_UNIFORM_SPEED_DURATION = 8 * 1000;

    /**
     * 默认加速后减速动画最大时长
     */
    public static final int MAX_DECELERATE_SPEED_DURATION = 450;

    /**
     * 95f-100f时，透明度1f-0f时长
     */
    public static final int DO_END_ALPHA_DURATION = 630;

    /**
     * 95f - 100f动画时长
     */
    public static final int DO_END_PROGRESS_DURATION = 500;

    /**
     * 当前匀速动画最大的时长
     */
    private static int CURRENT_MAX_UNIFORM_SPEED_DURATION = MAX_UNIFORM_SPEED_DURATION;

    /**
     * 当前加速后减速动画最大时长
     */
    private static int CURRENT_MAX_DECELERATE_SPEED_DURATION = MAX_DECELERATE_SPEED_DURATION;

    /**
     * 默认的高度(dp)
     */
    public static final int WEB_PROGRESS_DEFAULT_HEIGHT = 3;

    /**
     * 进度条颜色默认
     */
    public static final String WEB_PROGRESS_COLOR = "#2483D9";

    /**
     * 进度条颜色
     */
    private int mColor;

    /**
     * 进度条的画笔
     */
    private Paint mPaint;

    /**
     * 进度条动画
     */
    private Animator mAnimator;

    /**
     * 控件的宽度
     */
    private int mTargetWidth = 0;

    /**
     * 控件的高度
     */
    private int mTargetHeight;

    /**
     * 标志当前进度条的状态
     */
    private int TAG = 0;

    /**
     * 第一次过来进度show，后面就是setProgress
     */
    private boolean isShow = false;

    public static final int UN_START = 0;
    public static final int STARTED = 1;
    public static final int FINISH = 2;
    private float mCurrentProgress = 0F;

    public WebProgressView(Context context) {
        this(context, null);
    }

    public WebProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WebProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mColor = Color.parseColor(WEB_PROGRESS_COLOR);
        mPaint.setAntiAlias(true);
        mPaint.setColor(mColor);
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.SQUARE);

        mTargetWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        mTargetHeight = dip2px(WEB_PROGRESS_DEFAULT_HEIGHT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);

        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);

        if (wMode == MeasureSpec.AT_MOST) {
            w = Math.min(w, getContext().getResources().getDisplayMetrics().widthPixels);
        }
        if (hMode == MeasureSpec.AT_MOST) {
            h = mTargetHeight;
        }
        this.setMeasuredDimension(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.drawRect(0, 0, mCurrentProgress / 100 * (float) this.getWidth(), this.getHeight(), mPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mTargetWidth = getMeasuredWidth();
        int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        if (mTargetWidth >= screenWidth) {
            CURRENT_MAX_DECELERATE_SPEED_DURATION = MAX_DECELERATE_SPEED_DURATION;
            CURRENT_MAX_UNIFORM_SPEED_DURATION = MAX_UNIFORM_SPEED_DURATION;
        } else {
            //取比值
            float rate = this.mTargetWidth / (float) screenWidth;
            CURRENT_MAX_UNIFORM_SPEED_DURATION = (int) (MAX_UNIFORM_SPEED_DURATION * rate);
            CURRENT_MAX_DECELERATE_SPEED_DURATION = (int) (MAX_DECELERATE_SPEED_DURATION * rate);
        }
    }

    /**
     * 设置单色进度条
     */
    public void setColor(int color) {
        this.mColor = color;
        mPaint.setColor(color);
    }

    public void setColor(String color) {
        this.setColor(Color.parseColor(color));
    }

    public void setColor(int startColor, int endColor) {
        LinearGradient linearGradient = new LinearGradient(0, 0, mTargetWidth, mTargetHeight, startColor, endColor, Shader.TileMode.CLAMP);
        mPaint.setShader(linearGradient);
    }

    /**
     * 设置渐变色进度条
     *
     * @param startColor 开始颜色
     * @param endColor   结束颜色
     */
    public void setColor(String startColor, String endColor) {
        this.setColor(Color.parseColor(startColor), Color.parseColor(endColor));
    }

    private void setFinish() {
        isShow = false;
        TAG = FINISH;
    }

    private void startAnim(boolean isFinished) {

        float v = isFinished ? 100 : 95;

        if (mAnimator != null && mAnimator.isStarted()) {
            mAnimator.cancel();
        }
        mCurrentProgress = mCurrentProgress == 0 ? 0.00000001f : mCurrentProgress;
        // 可能由于透明度造成突然出现的问题
        setAlpha(1);

        if (!isFinished) {
            ValueAnimator mAnimator = ValueAnimator.ofFloat(mCurrentProgress, v);
            float residue = 1f - mCurrentProgress / 100 - 0.05f;
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.setDuration((long) (residue * CURRENT_MAX_UNIFORM_SPEED_DURATION));
            mAnimator.addUpdateListener(mAnimatorUpdateListener);
            mAnimator.start();
            this.mAnimator = mAnimator;
        } else {

            ValueAnimator segment95Animator = null;
            if (mCurrentProgress < 95) {
                segment95Animator = ValueAnimator.ofFloat(mCurrentProgress, 95);
                float residue = 1f - mCurrentProgress / 100f - 0.05f;
                segment95Animator.setInterpolator(new LinearInterpolator());
                segment95Animator.setDuration((long) (residue * CURRENT_MAX_DECELERATE_SPEED_DURATION));
                segment95Animator.setInterpolator(new DecelerateInterpolator());
                segment95Animator.addUpdateListener(mAnimatorUpdateListener);
            }

            ObjectAnimator mObjectAnimator = ObjectAnimator.ofFloat(this, "alpha", 1f, 0f);
            mObjectAnimator.setDuration(DO_END_ALPHA_DURATION);
            ValueAnimator mValueAnimatorEnd = ValueAnimator.ofFloat(95f, 100f);
            mValueAnimatorEnd.setDuration(DO_END_PROGRESS_DURATION);
            mValueAnimatorEnd.addUpdateListener(mAnimatorUpdateListener);

            AnimatorSet mAnimatorSet = new AnimatorSet();
            mAnimatorSet.playTogether(mObjectAnimator, mValueAnimatorEnd);

            if (segment95Animator != null) {
                AnimatorSet mAnimatorSet1 = new AnimatorSet();
                mAnimatorSet1.play(mAnimatorSet).after(segment95Animator);
                mAnimatorSet = mAnimatorSet1;
            }
            mAnimatorSet.addListener(mAnimatorListenerAdapter);
            mAnimatorSet.start();
            mAnimator = mAnimatorSet;
        }

        TAG = STARTED;
    }

    private final ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = animation -> {
        mCurrentProgress = (float) animation.getAnimatedValue();
        invalidate();
    };

    private final AnimatorListenerAdapter mAnimatorListenerAdapter = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            doEnd();
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // animator cause leak , if not cancel;
        if (mAnimator != null && mAnimator.isStarted()) {
            mAnimator.cancel();
            mAnimator = null;
        }
    }

    private void doEnd() {
        if (TAG == FINISH && mCurrentProgress == 100) {
            setVisibility(GONE);
            mCurrentProgress = 0f;
            this.setAlpha(1f);
        }
        TAG = UN_START;
    }

    public void reset() {
        mCurrentProgress = 0;
        if (mAnimator != null && mAnimator.isStarted()) {
            mAnimator.cancel();
        }
    }

    public void setProgress(int newProgress) {
        setProgress(Float.valueOf(newProgress));
    }

    public LayoutParams offerLayoutParams() {
        return new LayoutParams(mTargetWidth, mTargetHeight);
    }

    private int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public WebProgressView setHeight(int heightDp) {
        this.mTargetHeight = dip2px(heightDp);
        return this;
    }

    public void setProgress(float progress) {
        // fix 同时返回两个 100，产生两次进度条的问题；
        if (TAG == UN_START && progress == 100) {
            setVisibility(View.GONE);
            return;
        }

        if (getVisibility() == View.GONE) {
            setVisibility(View.VISIBLE);
        }
        if (progress < 95) {
            return;
        }
        if (TAG != FINISH) {
            startAnim(true);
        }
    }

    /**
     * 显示进度条
     */
    public void show() {
        isShow = true;
        setVisibility(View.VISIBLE);
        mCurrentProgress = 0f;
        startAnim(false);
    }

    /**
     * 进度完成后消失
     */
    public void hide() {
        setWebProgress(100);
    }

    /**
     * 为单独处理WebView进度条
     */
    public void setWebProgress(int newProgress) {
        if (newProgress >= 0 && newProgress < 95) {
            if (!isShow) {
                show();
            } else {
                setProgress(newProgress);
            }
        } else {
            setProgress(newProgress);
            setFinish();
        }
    }
}
