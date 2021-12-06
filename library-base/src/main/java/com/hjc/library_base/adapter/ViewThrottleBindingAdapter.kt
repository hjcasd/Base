package com.hjc.library_base.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.blankj.utilcode.util.ToastUtils

/**
 * @Author: HJC
 * @Date: 2021/12/6 10:38
 * @Description: onClick事件防抖处理(其他使用RxBinding)
 */
object ViewThrottleBindingAdapter {

    @BindingAdapter("android:onClick")
    @JvmStatic
    fun setViewOnClick(view: View, callback: View.OnClickListener) {
        view.setOnClickListener(ThrottleClickListener(callback))
    }

    class ThrottleClickListener(
        private val callback: View.OnClickListener
    ) : View.OnClickListener {

        // 上次点击时间
        private var mLastTime = 0L

        companion object {
            // 500毫秒之类的重复点击过滤
            private const val CLICK_THRESHOLD = 500
        }

        override fun onClick(v: View?) {
            val currentTime = System.currentTimeMillis()
            if (currentTime - mLastTime >= CLICK_THRESHOLD) {
                mLastTime = currentTime
                callback.onClick(v)
            } else {
                ToastUtils.showShort("点的太快了，歇会呗！")
            }
        }

    }
}