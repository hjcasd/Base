package com.hjc.library_common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:52
 * @Description: RecyclerView Adapter基类
 */
abstract class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val viewDataBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            getLayoutId(),
            parent,
            false
        )
        return MyViewHolder(viewDataBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    abstract fun getLayoutId(): Int

    class MyViewHolder(mBinding: ViewDataBinding) : RecyclerView.ViewHolder(
        mBinding.root
    )
}