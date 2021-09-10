package com.hjc.library_widget.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.hjc.library_widget.R

/**
 * @Author: HJC
 * @Date: 2021/9/10 14:15
 * @Description: 横向滑动的指示器
 */
class LineIndicatorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mBgPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBgRect: RectF = RectF()
    private var mRadius: Float = 0f
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mRect: RectF = RectF()
    private var viewWidth: Int = 0

    /**
     * 指示器背景颜色
     */
    private var mBgColor = Color.parseColor("#e5e5e5")

    /**
     * 指示器颜色
     */
    private var mIndicatorColor = Color.parseColor("#ff4646")

    /**
     * 指示器宽度
     */
    private var mIndicatorWidth = 30f

    /**
     * 滑动进度比例
     */
    var progressRatio: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {

        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.LineIndicatorView)
        mBgColor = typedArray.getColor(R.styleable.LineIndicatorView_indicatorBgColor, mBgColor)
        mIndicatorColor = typedArray.getColor(R.styleable.LineIndicatorView_indicatorColor, mIndicatorColor)
        mIndicatorWidth = typedArray.getDimensionPixelSize(R.styleable.LineIndicatorView_indicatorWidth, dip2px(mIndicatorWidth)).toFloat()
        typedArray.recycle()

        mBgPaint.color = mBgColor
        mBgPaint.style = Paint.Style.FILL
        mPaint.color = mIndicatorColor
        mPaint.style = Paint.Style.FILL

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        mBgRect.set(0f, 0f, w * 1f, h * 1f)
        mRadius = h / 2f
    }

    /**
     * 设置指示器背景进度条的颜色
     * @param color 背景色
     */
    fun setBgColor(@ColorInt color: Int) {
        mBgPaint.color = color
        invalidate()
    }

    /**
     * 设置指示器的颜色
     * @param color 指示器颜色
     */
    fun setIndicatorColor(@ColorInt color: Int) {
        mPaint.color = color
        invalidate()
    }

    /**
     * 绑定recyclerView
     */
    fun bindRecyclerView(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val offsetX = recyclerView.computeHorizontalScrollOffset()
                val range = recyclerView.computeHorizontalScrollRange()
                val extend = recyclerView.computeHorizontalScrollExtent()
                val progress: Float = offsetX * 1.0f / (range - extend)
                this@LineIndicatorView.progressRatio = progress
            }
        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //绘制背景
        canvas?.drawRoundRect(mBgRect, mRadius, mRadius, mBgPaint)

        //计算指示器的长度和位置
        val leftOffset = (viewWidth - mIndicatorWidth) * progressRatio
        val left = mBgRect.left + leftOffset
        val right = left + mIndicatorWidth
        mRect.set(left, mBgRect.top, right, mBgRect.bottom)

        //绘制指示器
        canvas?.drawRoundRect(mRect, mRadius, mRadius, mPaint)
    }

    private fun dip2px(dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}