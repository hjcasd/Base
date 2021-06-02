package com.hjc.library_common.view

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import com.hjc.library_common.R
import com.scwang.smart.refresh.layout.api.RefreshHeader
import com.scwang.smart.refresh.layout.api.RefreshKernel
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import com.scwang.smart.refresh.layout.constant.SpinnerStyle

/**
 * @Author: HJC
 * @Date: 2020/12/5 14:43
 * @Description: 自定义HeaderView
 */
class SmartCustomHeader @JvmOverloads constructor(
    mContext: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(
    mContext, attrs, defStyleAttr
), RefreshHeader {

    private var tvTitle: TextView

    private var animator: ObjectAnimator

    init {
        val view = LayoutInflater.from(mContext).inflate(R.layout.common_layout_my_header, this)
        val ivRefresh = view.findViewById<ImageView>(R.id.iv_refresh)
        tvTitle = view.findViewById(R.id.tv_title)

        animator = ObjectAnimator.ofFloat(ivRefresh, "rotation", 0f, 3600f)
        animator.duration = 10000
        animator.interpolator = null
        animator.repeatCount = Animation.INFINITE
        animator.repeatMode = ValueAnimator.RESTART
    }

    override fun getView(): View {
        return this
    }

    override fun getSpinnerStyle(): SpinnerStyle {
        return SpinnerStyle.Translate
    }

    override fun onStartAnimator(layout: RefreshLayout, headHeight: Int, maxDragHeight: Int) {
        animator.start()
    }

    override fun onFinish(layout: RefreshLayout, success: Boolean): Int {
        animator.cancel()
        if (success) {
            tvTitle.text = "刷新完成"
        } else {
            tvTitle.text = "刷新失败"
        }
        return 500
    }

    override fun onStateChanged(
        refreshLayout: RefreshLayout,
        oldState: RefreshState,
        newState: RefreshState
    ) {
        when (newState) {
            RefreshState.None, RefreshState.PullDownToRefresh -> tvTitle.text = "下拉开始刷新"
            RefreshState.Refreshing -> tvTitle.text = "正在刷新..."
            RefreshState.ReleaseToRefresh -> tvTitle.text = "释放立即刷新"
            else -> {
                tvTitle.text = "下拉开始刷新"
            }
        }
    }

    override fun isSupportHorizontalDrag(): Boolean {
        return false
    }

    override fun onInitialized(kernel: RefreshKernel, height: Int, maxDragHeight: Int) {

    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
    }

    override fun onReleased(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {

    }

    override fun onHorizontalDrag(percentX: Float, offsetX: Int, offsetMax: Int) {

    }

    override fun setPrimaryColors(@ColorInt vararg colors: Int) {

    }
}