package com.hjc.library_widget.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;


/**
 * @Author: HJC
 * @Date: 2019/1/7 11:29
 * @Description: RecyclerView的网格分割线
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private final Drawable mDrawable;

    public GridItemDecoration(Context context, int resId) {
        //获取 Drawable 对象
        mDrawable = ContextCompat.getDrawable(context, resId);
    }

    @Override
    public void onDraw(@NonNull Canvas canvas, RecyclerView parent, @NonNull RecyclerView.State state) {
        //绘制水平方向
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View mChildView = parent.getChildAt(i);
            RecyclerView.LayoutParams mLayoutParams = (RecyclerView.LayoutParams) mChildView.getLayoutParams();
            int left = mChildView.getLeft() - mLayoutParams.leftMargin;
            int right = mChildView.getRight() + mDrawable.getIntrinsicWidth() + mLayoutParams.rightMargin;
            int top = mChildView.getBottom() + mLayoutParams.bottomMargin;
            int bottom = top + mDrawable.getIntrinsicHeight();
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }

        //绘制垂直方向
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams mLayoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            int left = childView.getRight() + mLayoutParams.rightMargin;
            int right = left + mDrawable.getIntrinsicWidth();
            int top = childView.getTop() - mLayoutParams.topMargin;
            int bottom = childView.getBottom() + mLayoutParams.bottomMargin;
            mDrawable.setBounds(left, top, right, bottom);
            mDrawable.draw(canvas);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int bottom = mDrawable.getIntrinsicHeight();
        int right = mDrawable.getIntrinsicWidth();
        //如果是最后一列，留出位置
        if (isLastCol(view, parent)) {
            right = 0;
        }
        // 如果是最后一行，留出位置
        if (isLastRow(view, parent)) {
            bottom = 0;
        }
        outRect.bottom = bottom;
        outRect.right = right;
    }

    /**
     * 是否是最后一列
     * （当前位置+1）% 列数 ==0，判断是否为最后一列
     */
    private boolean isLastCol(View view, RecyclerView parent) {
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        return (currentPosition + 1) % spanCount == 0;
    }

    /**
     * 是否是最后一行
     * 当前位置+1 > (行数-1)*列数
     */
    private boolean isLastRow(View view, RecyclerView parent) {
        int currentPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        int spanCount = getSpanCount(parent);
        int rowNum = Objects.requireNonNull(parent.getAdapter()).getItemCount() / spanCount == 0 ? parent.getAdapter().getItemCount() / spanCount : (parent.getAdapter().getItemCount() / spanCount + 1);
        return (currentPosition + 1) > (rowNum - 1) * spanCount;
    }

    /**
     * 获取列数
     * 如果是GridView，就获取列数，如果是ListView，列数就是1
     */
    private int getSpanCount(RecyclerView parent) {
        RecyclerView.LayoutManager mLayoutManager = parent.getLayoutManager();
        if (mLayoutManager instanceof GridLayoutManager) {
            GridLayoutManager mGridLayoutManager = (GridLayoutManager) mLayoutManager;
            return mGridLayoutManager.getSpanCount();
        }
        return 1;
    }

}