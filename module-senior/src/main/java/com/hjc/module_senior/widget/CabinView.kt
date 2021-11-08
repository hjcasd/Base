package com.hjc.module_senior.widget

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.hjc.module_senior.R
import java.util.*
import kotlin.collections.HashSet

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 圆角矩形View
 */
class CabinView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(mContext, attrs, defStyleAttr) {

    /**
     * 圆角大小
     */
    private var mCabinCornerRadius = 0f

    /**
     * 内容填充色
     */
    private var mCabinContentColor = 0

    /**
     * 内容透明度
     */
    private var mCabinContentAlpha = 0

    /**
     * 边框宽度
     */
    private var mCabinStrokeWidth = 0f

    /**
     * 边框颜色
     */
    private var mCabinStrokeColor = 0

    private val mContentPaint: Paint = Paint()
    private val mStrokePaint: Paint = Paint()

    private var mWidth = 0
    private var mHeight = 0

    private var originalRectF = RectF(200f, 200f, 300f, 400f)

    init {
        initTypeArray(attrs)
        initPaint()
    }

    private fun initTypeArray(attrs: AttributeSet?) {
        val typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.CabinView)
        mCabinCornerRadius = typeArray.getDimensionPixelSize(R.styleable.CabinView_cabinCornerRadius, ConvertUtils.dp2px(8f)).toFloat()
        mCabinContentAlpha = typeArray.getInteger(R.styleable.CabinView_cabinContentAlpha, 0)
        mCabinContentColor = typeArray.getColor(R.styleable.CabinView_cabinContentColor, Color.BLACK)
        mCabinStrokeWidth = typeArray.getDimensionPixelSize(R.styleable.CabinView_cabinStrokeWidth, ConvertUtils.dp2px(1f)).toFloat()
        mCabinStrokeColor = typeArray.getColor(R.styleable.CabinView_cabinStrokeColor, Color.BLUE)

        typeArray.recycle()
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private fun initPaint() {
        mContentPaint.style = Paint.Style.FILL
        mContentPaint.color = mCabinContentColor
        // setAlpha()要放在setColor()方法之后才能生效
        mContentPaint.alpha = mCabinContentAlpha

        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.isAntiAlias = true
        mStrokePaint.strokeWidth = mCabinStrokeWidth
        mStrokePaint.color = mCabinStrokeColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        mWidth = widthSize
        mHeight = heightSize

        LogUtils.e("height: $mHeight")

        setMeasuredDimension(widthSize, heightSize)
    }

    fun startAnimation(newRectF: RectF) {
        val yOffset = newRectF.bottom - originalRectF.bottom
        ObjectAnimator
            .ofFloat(this, "translationY", 0f, yOffset)
            .setDuration(500)
            .start()

//        val originalHeight = originalRectF.bottom - originalRectF.top
//        val newHeight = newRectF.bottom - newRectF.top
//
//        val valueAnimator = ValueAnimator.ofFloat(newHeight)
//        valueAnimator.duration = 500
//        valueAnimator.start()
//        valueAnimator.addUpdateListener { animation ->
//            val params = layoutParams
//            val value = animation.animatedValue as Float
//            LogUtils.e("value: $value")
//            params.height = value.toInt()
//            requestLayout()
//        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRoundRect(originalRectF, mCabinCornerRadius, mCabinCornerRadius, mContentPaint)
        canvas.drawRoundRect(originalRectF, mCabinCornerRadius, mCabinCornerRadius, mStrokePaint)
    }

    private class ViewWrapper(private val view: View) {
        var width: Int
            get() = view.layoutParams.width
            set(width) {
                view.layoutParams.width = width
                view.requestLayout()
            }
        var height: Int
            get() = view.layoutParams.height
            set(height) {
                view.layoutParams.height = height
                view.requestLayout()
            }
    }

}