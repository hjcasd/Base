package com.hjc.base.ui.frame.activity.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.hjc.base.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * @Author: HJC
 * @Date: 2020/12/5 14:43
 * @Description: 自定义HeaderView
 */
public class MyHeader extends LinearLayout implements RefreshHeader {

    private TextView tvTitle;
    private ImageView ivRefresh;
    private ImageView ivLogo;
    private ObjectAnimator animator;

    public MyHeader(Context context) {
        super(context);
        initView(context);
    }

    public MyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.initView(context);
    }

    public MyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_my_header, this);
        ivLogo = view.findViewById(R.id.iv_logo);
        ivRefresh = view.findViewById(R.id.iv_refresh);
        tvTitle = view.findViewById(R.id.tv_title);

        animator = ObjectAnimator.ofFloat(ivRefresh, "rotation", 0f, 3600f);
        animator.setDuration(10000);
        animator.setInterpolator(null);
        animator.setRepeatCount(Animation.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
    }

    @NonNull
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout layout, int headHeight, int maxDragHeight) {
        animator.start();
    }

    @Override
    public int onFinish(@NonNull RefreshLayout layout, boolean success) {
        animator.cancel();
        if (success) {
            tvTitle.setText("刷新完成");
        } else {
            tvTitle.setText("刷新失败");
        }
        return 500;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                tvTitle.setText("下拉开始刷新");
                break;
            case Refreshing:
                tvTitle.setText("正在刷新...");
                break;
            case ReleaseToRefresh:
                tvTitle.setText("释放立即刷新");
                break;
        }
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {

    }
}
