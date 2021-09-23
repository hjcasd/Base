package com.hjc.library_base.adapter

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewpager.widget.PagerAdapter

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:51
 * @Description: PagerAdapter基类
 */
abstract class BasePagerAdapter<T>(protected var mContext: Context, list: List<T>) :
    PagerAdapter() {

    /**
     * 缓存的View集合
     */
    private val mViews: SparseArray<View> = SparseArray(list.size)

    /**
     * 数据源
     */
    private var mDataList: List<T>? = list

    /**
     * Item点击监听接口回调
     */
    private var mOnItemClickListener: OnItemClickListener? = null


    override fun getCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun isViewFromObject(view: View, any: Any): Boolean {
        return view === any
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view = mViews[position]
        if (view == null) {
            view = View.inflate(mContext, getLayoutId(), null)
            initView(view, position)
            mViews.put(position, view)
        }
        view.setOnClickListener { v: View? ->
            mOnItemClickListener?.onItemClick(v, position)
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
        container.removeView(mViews[position])
    }

    /**
     * 获取布局ID
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化View
     */
    abstract fun initView(itemView: View?, position: Int)

    /**
     * Item监听接口
     */
    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    /**
     * 设置监听
     */
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

}