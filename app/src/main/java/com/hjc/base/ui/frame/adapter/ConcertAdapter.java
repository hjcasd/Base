package com.hjc.base.ui.frame.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hjc.base.R;
import com.hjc.base.bean.ConcertBean;
import com.hjc.base.databinding.ItemConcertBinding;


public class ConcertAdapter extends PagedListAdapter<ConcertBean, ConcertAdapter.MyViewHolder> {

    private static final DiffUtil.ItemCallback<ConcertBean> mDiffCallback = new ConcertAdapter.ConcertInfoItemCallback();

    public ConcertAdapter() {
        super(mDiffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConcertBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_concert, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ConcertBean concertBean = getItem(position);
        if (concertBean != null) {
            holder.mBinding.setConcertBean(concertBean);
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemConcertBinding mBinding;

        MyViewHolder(ItemConcertBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }
    }

    private static class ConcertInfoItemCallback extends DiffUtil.ItemCallback<ConcertBean> {

        @Override
        public boolean areItemsTheSame(@NonNull ConcertBean oldItem, @NonNull ConcertBean newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }

        @Override
        public boolean areContentsTheSame(@NonNull ConcertBean oldItem, @NonNull ConcertBean newItem) {
            return oldItem.equals(newItem);
        }
    }
}
