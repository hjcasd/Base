package com.hjc.library_base.adapter

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:51
 * @Description: PagerAdapter基类
 */
abstract class BasePagerAdapter<T>(protected var mContext: Context, list: List<T>) :
    PagerAdapter() {

    private val mViews: SparseArray<View> = SparseArray(list.size)

    private var mDataList: List<T>? = list

    private var mOnItemClickListener: OnItemClickListener? = null

    override fun getCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
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

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mViews[position])
    }

    abstract fun getLayoutId(): Int

    abstract fun initView(itemView: View?, position: Int)

    interface OnItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener?) {
        mOnItemClickListener = onItemClickListener
    }

}