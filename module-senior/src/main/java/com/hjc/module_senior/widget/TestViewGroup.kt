package com.hjc.module_senior.widget

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 测试ViewGroup
 */
class TestViewGroup @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ViewGroup(mContext, attrs, defStyleAttr) {

    /**
     * 设置当前ViewGroup的LayoutParams
     */
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(mContext, attrs)
    }

    /**
     *  MeasureSpec的specMode,一共三种类型：
     *  EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     *  AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     *  UNSPECIFIED：表示子布局想要多大就多大，很少使用
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 获取宽高和测量模式
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 测量所有的ChildView的宽高
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        // 用于计算左边两个childView的高度
        var leftHeight = 0
        // 用于计算右边两个childView的高度，最终高度取二者之间大值
        var rightHeight = 0

        // 用于计算上边两个childView的宽度
        var topWidth = 0
        // 用于计算下面两个childView的宽度，最终宽度取二者之间大值
        var bottomWidth = 0

        // 根据childView计算的出的宽和高，以及设置的margin计算容器的宽和高，主要用于容器是warp_content时
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            val childWidth = childView.measuredWidth
            val childHeight = childView.measuredHeight
            val childParams = childView.layoutParams as MarginLayoutParams

            // 上面两个childView
            if (i == 0 || i == 1) {
                topWidth += childWidth + childParams.leftMargin + childParams.rightMargin
            }
            if (i == 2 || i == 3) {
                bottomWidth += childWidth + childParams.leftMargin + childParams.rightMargin
            }
            if (i == 0 || i == 2) {
                leftHeight += childHeight + childParams.topMargin + childParams.bottomMargin
            }
            if (i == 1 || i == 3) {
                rightHeight += childHeight + childParams.topMargin + childParams.bottomMargin
            }
        }

        val parentWidth = max(topWidth, bottomWidth)
        val parentHeight = max(leftHeight, rightHeight)

        // 如果是wrap_content设置为我们计算的值,否则直接设置为父容器计算的值
        setMeasuredDimension(
            if (widthMode == MeasureSpec.EXACTLY) widthSize else parentWidth,
            if (heightMode == MeasureSpec.EXACTLY) heightSize else parentHeight
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childWidth: Int
        var childHeight: Int

        // 遍历所有childView根据其宽和高，以及margin进行布局
        for (i in 0 until childCount) {
            val childView = getChildAt(i)
            childWidth = childView.measuredWidth
            childHeight = childView.measuredHeight
            val childParams = childView.layoutParams as MarginLayoutParams

            var childLeftPos = 0
            var childTopPos = 0
            when (i) {
                0 -> {
                    childLeftPos = childParams.leftMargin
                    childTopPos = childParams.topMargin
                }
                1 -> {
                    childLeftPos = width - childWidth - childParams.leftMargin - childParams.rightMargin
                    childTopPos = childParams.topMargin
                }
                2 -> {
                    childLeftPos = childParams.leftMargin
                    childTopPos = height - childHeight - childParams.bottomMargin
                }
                3 -> {
                    childLeftPos = width - childWidth - childParams.leftMargin - childParams.rightMargin
                    childTopPos = height - childHeight - childParams.bottomMargin
                }
            }
            val childRightPos = childLeftPos + childWidth
            val childBottomPos = childTopPos + childHeight
            childView.layout(childLeftPos, childTopPos, childRightPos, childBottomPos)
        }
    }

}