package com.hjc.baselib.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:52
 * @Description: RecyclerView Adapter基类
 */
public abstract class BaseMvmRecyclerAdapter extends RecyclerView.Adapter<BaseMvmRecyclerAdapter.MyViewHolder> {

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getLayoutId(), parent, false);
        return new MyViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    protected abstract int getLayoutId();

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding mBinding;

        MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }

}
