package com.hjc.module_senior.widget.cabin

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.blankj.utilcode.util.ConvertUtils
import com.hjc.module_senior.R

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 带蒙层的ImageView
 */
class CabinImageView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageView(mContext, attrs, defStyleAttr) {

    /**
     * 圆角大小
     */
    private var mMaskCornerRadius = 0f

    /**
     * 内容填充色
     */
    private var mMaskContentColor = 0

    /**
     * 内容透明度
     */
    private var mMaskContentAlpha = 0

    /**
     * 边框宽度
     */
    private var mMaskStrokeWidth = 0f

    /**
     * 边框颜色
     */
    private var mMaskStrokeColor = 0

    /**
     * 模糊半径
     */
    private var mMaskBlurRadius = 0f

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
        val typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.CabinImageView)
        mMaskCornerRadius = typeArray.getDimensionPixelSize(R.styleable.CabinImageView_maskCornerRadius, ConvertUtils.dp2px(8f)).toFloat()
        mMaskContentAlpha = typeArray.getInteger(R.styleable.CabinImageView_maskContentAlpha, 0)
        mMaskContentColor = typeArray.getColor(R.styleable.CabinImageView_maskContentColor, Color.GREEN)
        mMaskStrokeWidth = typeArray.getDimensionPixelSize(R.styleable.CabinImageView_maskStrokeWidth, ConvertUtils.dp2px(1f)).toFloat()
        mMaskStrokeColor = typeArray.getColor(R.styleable.CabinImageView_maskStrokeColor, Color.BLUE)
        mMaskBlurRadius = typeArray.getDimensionPixelSize(R.styleable.CabinImageView_maskBlurRadius, ConvertUtils.dp2px(10f)).toFloat()

        typeArray.recycle()

        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private fun initPaint() {
        // 初始化内容画笔
        mContentPaint.style = Paint.Style.FILL
        mContentPaint.color = mMaskContentColor
        // setAlpha()要放在setColor()方法之后才能生效
        mContentPaint.alpha = mMaskContentAlpha

        val blurMaskFilter = BlurMaskFilter(mMaskBlurRadius, BlurMaskFilter.Blur.SOLID)
        mContentPaint.maskFilter = blurMaskFilter

        // 初始化边框画笔
        mStrokePaint.style = Paint.Style.STROKE
        mStrokePaint.isAntiAlias = true
        mStrokePaint.strokeWidth = mMaskStrokeWidth
        mStrokePaint.color = mMaskStrokeColor
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
        canvas.drawRoundRect(mCurrentRectF, mMaskCornerRadius, mMaskCornerRadius, mContentPaint)
        canvas.drawRoundRect(mCurrentRectF, mMaskCornerRadius, mMaskCornerRadius, mStrokePaint)
    }

}