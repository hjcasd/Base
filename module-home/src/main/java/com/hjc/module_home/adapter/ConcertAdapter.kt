package com.hjc.module_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeItemConcertBinding
import com.hjc.module_home.entity.ConcertBean

class ConcertAdapter : PagedListAdapter<ConcertBean, ConcertAdapter.MyViewHolder>(mDiffCallback) {

    companion object {
        private val mDiffCallback: DiffUtil.ItemCallback<ConcertBean> = ConcertInfoItemCallback()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: HomeItemConcertBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.home_item_concert,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val concertBean = getItem(position)
        if (concertBean != null) {
            holder.mBinding.concertBean = concertBean
        }
    }

    class MyViewHolder(var mBinding: HomeItemConcertBinding) : RecyclerView.ViewHolder(
        mBinding.root
    )

    private class ConcertInfoItemCallback : DiffUtil.ItemCallback<ConcertBean>() {

        override fun areItemsTheSame(oldItem: ConcertBean, newItem: ConcertBean): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: ConcertBean, newItem: ConcertBean): Boolean {
            return oldItem == newItem
        }
    }

}