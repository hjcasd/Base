package com.hjc.module_senior.widget.cabin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.hjc.module_senior.R

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 圆角矩形View
 */
class CabinMaskView @JvmOverloads constructor(
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

    /**
     * 模糊半径
     */
    private var mCabinBlurRadius = 0f

    /**
     * 内容画笔
     */
    private val mContentPaint: Paint = Paint()

    /**
     * 边框画笔
     */
    private val mStrokePaint: Paint = Paint()

    /**
     * 当前矩形
     */
    private var mCurrentRectF: RectF = RectF(0f, 0f, 0f, 0f)

    init {
        initTypeArray(attrs)
        initPaint()
    }

    private fun initTypeArray(attrs: AttributeSet?) {
        val typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.CabinMaskView)
        mCabinCornerRadius = typeArray.getDimensionPixelSize(R.styleable.CabinMaskView_cabinCornerRadius, ConvertUtils.dp2px(8f)).toFloat()
        mCabinContentAlpha = typeArray.getInteger(R.styleable.CabinMaskView_cabinContentAlpha, 0)
        mCabinContentColor = typeArray.getColor(R.styleable.CabinMaskView_cabinContentColor, Color.GREEN)
        mCabinStrokeWidth = typeArray.getDimensionPixelSize(R.styleable.CabinMaskView_cabinStrokeWidth, ConvertUtils.dp2px(1f)).toFloat()
        mCabinStrokeColor = typeArray.getColor(R.styleable.CabinMaskView_cabinStrokeColor, Color.BLUE)
        mCabinBlurRadius = typeArray.getDimensionPixelSize(R.styleable.CabinMaskView_cabinBlurRadius, ConvertUtils.dp2px(10f)).toFloat()

        typeArray.recycle()

        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private fun initPaint() {
        // 初始化内容画笔
        mContentPaint.style = Paint.Style.FILL
        mContentPaint.color = mCabinContentColor
        // setAlpha()要放在setColor()方法之后才能生效
        mContentPaint.alpha = mCabinContentAlpha

        val blurMaskFilter = BlurMaskFilter(mCabinBlurRadius, BlurMaskFilter.Blur.SOLID)
        mContentPaint.maskFilter = blurMaskFilter

        // 初始化边框画笔
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.isAntiAlias = true
        mStrokePaint.strokeWidth = mCabinStrokeWidth
        mStrokePaint.color = mCabinStrokeColor
    }
    
    fun setRect(rectF: RectF){
        mCurrentRectF = rectF
        invalidate()
    }

    /**
     * 开始动画
     */
    fun startAnimation(newRectF: RectF) {
        val valueAnimator = ValueAnimator.ofObject(MaskRectEvaluator(), mCurrentRectF, newRectF)
        valueAnimator.duration = 500
        valueAnimator.start()
        valueAnimator.addUpdateListener { animation ->
            mCurrentRectF = animation.animatedValue as RectF
            invalidate()
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                mCurrentRectF = newRectF
            }

        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawRoundRect(canvas)
    }

    /**
     * 画圆角矩形
     */
    private fun drawRoundRect(canvas: Canvas) {
        canvas.drawRoundRect(mCurrentRectF, mCabinCornerRadius, mCabinCornerRadius, mContentPaint)
        canvas.drawRoundRect(mCurrentRectF, mCabinCornerRadius, mCabinCornerRadius, mStrokePaint)
    }

}