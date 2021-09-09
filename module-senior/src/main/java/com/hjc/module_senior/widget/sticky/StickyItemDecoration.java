package com.hjc.module_senior.widget.sticky;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HJC
 * @Date: 2021/9/9 16:54
 * @Description: 自定义实现置顶View的分割线
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 吸附的itemView
     */
    private View mStickyItemView;

    /**
     * 吸附itemView 距离顶部
     */
    private int mStickyItemViewMarginTop;

    /**
     * 吸附itemView 高度
     */
    private int mStickyItemViewHeight;

    /**
     * 通过它获取到需要吸附view的相关信息
     */
    private final IStickyView mStickyView;

    /**
     * adapter
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;

    /**
     * viewHolder
     */
    private RecyclerView.ViewHolder mViewHolder;

    /**
     * position list
     */
    private final List<Integer> mStickyPositionList = new ArrayList<>();

    /**
     * layout manager
     */
    private LinearLayoutManager mLayoutManager;

    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;

    public StickyItemDecoration() {
        mStickyView = new CustomStickyView();
        initPaint();
    }

    /**
     * init paint
     */
    private void initPaint() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
    }


    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null){
            return;
        }
        if (adapter.getItemCount() <= 0) return;

        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();

        // 滚动过程中当前的UI是否可以找到吸附的view
        boolean mCurrentUIFindStickView = false;
        clearStickyPositionList();

        for (int m = 0, size = parent.getChildCount(); m < size; m++) {
            View view = parent.getChildAt(m);

            // 如果是吸附的view
            if (mStickyView.isStickyView(view)) {
                mCurrentUIFindStickView = true;
                getStickyViewHolder(parent);
                cacheStickyViewPosition(m);

                if (view.getTop() <= 0) {
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());
                } else {
                    if (mStickyPositionList.size() > 0) {
                        if (mStickyPositionList.size() == 1) {
                            bindDataForStickyView(mStickyPositionList.get(0), parent.getMeasuredWidth());
                        } else {
                            int currentPosition = getStickyViewPositionOfRecyclerView(m);
                            int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);
                            if (indexOfCurrentPosition >= 1)
                                bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                        }
                    }
                }

                if (view.getTop() > 0 && view.getTop() <= mStickyItemViewHeight) {
                    mStickyItemViewMarginTop = mStickyItemViewHeight - view.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                    View nextStickyView = getNextStickyView(parent);
                    if (nextStickyView != null && nextStickyView.getTop() <= mStickyItemViewHeight) {
                        mStickyItemViewMarginTop = mStickyItemViewHeight - nextStickyView.getTop();
                    }
                }

                drawStickyItemView(c);
                break;
            }
        }

        if (!mCurrentUIFindStickView) {
            mStickyItemViewMarginTop = 0;
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == adapter.getItemCount() && mStickyPositionList.size() > 0) {
                bindDataForStickyView(mStickyPositionList.get(mStickyPositionList.size() - 1), parent.getMeasuredWidth());
            }
            drawStickyItemView(c);
        }
    }

    /**
     * 清空吸附position集合
     */
    private void clearStickyPositionList() {
        if (mLayoutManager.findFirstVisibleItemPosition() == 0) {
            mStickyPositionList.clear();
        }
    }

    /**
     * 得到下一个吸附View
     */
    private View getNextStickyView(RecyclerView parent) {
        int num = 0;
        View nextStickyView = null;
        for (int m = 0, size = parent.getChildCount(); m < size; m++) {
            View view = parent.getChildAt(m);
            if (mStickyView.isStickyView(view)) {
                nextStickyView = view;
                num++;
            }
            if (num == 2) break;
        }
        return num >= 2 ? nextStickyView : null;
    }

    /**
     * 给StickyView绑定数据
     */
    private void bindDataForStickyView(int position, int width) {
        if (mBindDataPosition == position || mViewHolder == null) return;

        mBindDataPosition = position;
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);
        measureLayoutStickyItemView(width);
        mStickyItemViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();
    }

    /**
     * 缓存吸附的view position
     */
    private void cacheStickyViewPosition(int m) {
        int position = getStickyViewPositionOfRecyclerView(m);
        if (!mStickyPositionList.contains(position)) {
            mStickyPositionList.add(position);
        }
    }

    /**
     * 得到吸附view在RecyclerView中 的position
     */
    private int getStickyViewPositionOfRecyclerView(int m) {
        return mLayoutManager.findFirstVisibleItemPosition() + m;
    }

    /**
     * 得到吸附viewHolder
     */
    private void getStickyViewHolder(RecyclerView recyclerView) {
        if (mAdapter != null) return;

        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, mStickyView.getStickViewType());
        mStickyItemView = mViewHolder.itemView;
    }

    /**
     * 计算布局吸附的itemView
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (mStickyItemView == null || !mStickyItemView.isLayoutRequested()) return;

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);
        mStickyItemView.layout(0, 0, mStickyItemView.getMeasuredWidth(), mStickyItemView.getMeasuredHeight());
    }

    /**
     * 绘制吸附的itemView
     */
    private void drawStickyItemView(Canvas canvas) {
        if (mStickyItemView == null) return;

        int saveCount = canvas.save();
        canvas.translate(0, -mStickyItemViewMarginTop);
        mStickyItemView.draw(canvas);
        canvas.restoreToCount(saveCount);
    }
}
