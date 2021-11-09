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


    private var mCabinShadowStartColor = 0
    private var mCabinShadowEndColor = 0
    private var mCabinShadowRadius = 0f


    /**
     * 边框颜色
     */
    private var mCabinStrokeColor = 0

    /**
     * 内容画笔
     */
    private val mContentPaint: Paint = Paint()

    /**
     * 边框画笔
     */
    private val mStrokePaint: Paint = Paint()

    /**
     * 阴影画笔
     */
    private val mShadowPaint: Paint = Paint()

    /**
     * 初始矩形
     */
    private var mOriginalRectF = RectF(200f, 100f, 300f, 300f)

    /**
     * 当前矩形
     */
    private var mCurrentRectF: RectF? = null

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

        mCabinShadowStartColor = typeArray.getColor(R.styleable.CabinView_cabinShadowStartColor, Color.RED)
        mCabinShadowEndColor = typeArray.getColor(R.styleable.CabinView_cabinShadowEndColor, Color.RED)
        mCabinShadowRadius = typeArray.getDimensionPixelSize(R.styleable.CabinView_cabinShadowRadius, ConvertUtils.dp2px(10f)).toFloat()

        typeArray.recycle()
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

        val linearGradient = LinearGradient(
            0f,
            0f,
            10f,
            0f,
            mCabinShadowStartColor,
            mCabinShadowEndColor,
            Shader.TileMode.CLAMP
        )
        mShadowPaint.shader = linearGradient

        val blurMaskFilter = BlurMaskFilter(mCabinShadowRadius, BlurMaskFilter.Blur.NORMAL)
        mShadowPaint.maskFilter = blurMaskFilter

        setLayerType(LAYER_TYPE_SOFTWARE, mShadowPaint)
    }

    /**
     * 开始动画
     */
    fun startAnimation(newRectF: RectF) {
        val valueAnimator = ValueAnimator.ofObject(CabinRectEvaluator(), mOriginalRectF, newRectF)
        valueAnimator.duration = 500
        valueAnimator.start()
        valueAnimator.addUpdateListener { animation ->
            mCurrentRectF = animation.animatedValue as RectF
            invalidate()
        }
        valueAnimator.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator?) {
                mOriginalRectF = newRectF
            }

        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mCurrentRectF == null) {
            mCurrentRectF = mOriginalRectF
            drawRoundRect(canvas)
        } else {
            drawRoundRect(canvas)
        }
    }

    private fun drawRoundRect(canvas: Canvas) {
        mCurrentRectF?.let {
            canvas.drawRoundRect(it, mCabinShadowRadius, mCabinShadowRadius, mShadowPaint)
            canvas.drawRoundRect(it, mCabinCornerRadius, mCabinCornerRadius, mContentPaint)
            canvas.drawRoundRect(it, mCabinCornerRadius, mCabinCornerRadius, mStrokePaint)
        }
    }

}