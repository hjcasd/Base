package com.hjc.base.widget.marquee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/7/25 14:29
 * @Description: MarqueeLayout 基类adapter
 */
public abstract class MarqueeBaseAdapter<T> extends BaseAdapter {
    private List<T> mDataList;
    protected Context mContext;

    public MarqueeBaseAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mDataList = list;
    }

    @Override
    public int getCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @Override
    public T getItem(int position) {
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(mContext).inflate(getItemLayoutId(), parent, false);
        initView(itemView, position, getItem(position));
        return itemView;
    }

    protected abstract int getItemLayoutId();

    protected abstract void initView(View itemView, int position, T item);
}
