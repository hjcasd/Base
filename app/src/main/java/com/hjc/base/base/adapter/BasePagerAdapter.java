package com.hjc.base.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:51
 * @Description: PagerAdapter基类
 */
public abstract class BasePagerAdapter<T> extends PagerAdapter {
    protected Context mContext;
    private SparseArray<View> mViews;
    protected List<T> mDataList;

    public BasePagerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mDataList = list;
        mViews = new SparseArray<>(list.size());
    }

    @Override
    public int getCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        if (view == null) {
            view = View.inflate(mContext, getLayoutId(), null);
            initView(view, position);
            mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    protected abstract int getLayoutId();

    protected abstract void initView(View itemView, int position);
}
