package com.hjc.library_widget.bar

import android.view.View

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:25
 * @Description: TitleBar左右点击事件
 */
interface OnViewClickListener {
    fun leftClick(view: View?)
    fun rightClick(view: View?)
}