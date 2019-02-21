package com.hjc.base.widget.marquee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * MarqueeLayout 基类adapter
 */
public abstract class MarqueeBaseAdapter<T> extends BaseAdapter {
    private List<T> mDataList;
    protected Context mContext;

    private OnItemClickListener mItemClickListener;

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

        if (mItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onClick(v, position);
                }
            });
        }
        return itemView;
    }

    protected abstract int getItemLayoutId();

    protected abstract void initView(View itemView, int position, T item);

    public interface OnItemClickListener {
        void onClick(View view, int position);
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }
}
