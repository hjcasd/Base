package com.hjc.baselib.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:52
 * @Description: RecyclerView Adapter基类
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    private List<T> mDataList;

    public BaseRecyclerAdapter(Context context, List<T> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(getLayoutId(), parent, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        initView(holder);
        addListener(mDataList.get(position), position);
        bindData(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() : 0;
    }

    protected abstract int getLayoutId();

    protected abstract void initView(BaseViewHolder holder);

    protected abstract void bindData(T data, int position);

    protected abstract void addListener(T data, int position);

}
