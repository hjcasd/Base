package com.hjc.module_senior.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.blankj.utilcode.util.ConvertUtils
import com.hjc.module_senior.R
import java.util.*
import kotlin.collections.HashSet

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 测试View
 */
class TestView @JvmOverloads constructor(
    private val mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(mContext, attrs, defStyleAttr) {

    /**
     * 文本
     */
    private var mTitleText = ""

    /**
     * 文本字体大小
     */
    private var mTitleTextSize = 14f

    /**
     * 文本字体颜色
     */
    private var mTitleTextColor = Color.RED

    /**
     * 画笔
     */
    private val mPaint: Paint = Paint()

    /**
     * 文字边界Rect
     */
    private var mTextBoundRect = Rect()


    init {
        initTypeArray(attrs)
        initView()

    }

    private fun initTypeArray(attrs: AttributeSet?) {
        val typeArray = mContext.obtainStyledAttributes(attrs, R.styleable.TestView)
        mTitleText = typeArray.getString(R.styleable.TestView_customText).toString()
        mTitleTextSize = typeArray.getDimensionPixelSize(R.styleable.TestView_customTextSize, ConvertUtils.sp2px(18f)).toFloat()
        mTitleTextColor = typeArray.getColor(R.styleable.TestView_customTextColor, Color.BLACK)
        typeArray.recycle()
    }

    private fun initView() {
        initPaint()

        this.setOnClickListener {
            mTitleText = randomText()
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length, mTextBoundRect)
            invalidate()
        }
    }

    private fun initPaint() {
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 20f
        mPaint.isAntiAlias = true
        mPaint.textSize = mTitleTextSize
        mPaint.getTextBounds(mTitleText, 0, mTitleText.length, mTextBoundRect)
    }

    private fun randomText(): String {
        val random = Random()
        val set: MutableSet<Int> = HashSet()
        while (set.size < 4) {
            val randomInt: Int = random.nextInt(10)
            set.add(randomInt)
        }
        val sb = StringBuffer()
        for (i in set) {
            sb.append("" + i)
        }
        return sb.toString()
    }

    /**
     *  MeasureSpec的specMode,一共三种类型：
     *  EXACTLY：一般是设置了明确的值或者是MATCH_PARENT
     *  AT_MOST：表示子布局限制在一个最大值内，一般为WARP_CONTENT
     *  UNSPECIFIED：表示子布局想要多大就多大，很少使用
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        // 计算宽度
        val width: Int
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize
        } else {
            mPaint.textSize = mTitleTextSize
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length, mTextBoundRect)
            val textWidth = mTextBoundRect.width()
            val measureWidth = paddingLeft + textWidth + paddingRight
            width = measureWidth
        }

        // 计算高度
        val height: Int
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize
        } else {
            mPaint.textSize = mTitleTextSize
            mPaint.getTextBounds(mTitleText, 0, mTitleText.length, mTextBoundRect)
            val textHeight = mTextBoundRect.height()
            val measureHeight = paddingTop + textHeight + paddingBottom
            height = measureHeight
        }

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画背景
        mPaint.color = ContextCompat.getColor(context, R.color.senior_green)
        canvas.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), mPaint)

        // 画文字居中
        mPaint.color = mTitleTextColor
        val x = width / 2 - mTextBoundRect.width() / 2
        val y = height / 2 + mTextBoundRect.height() / 2
        canvas.drawText(mTitleText, x.toFloat(), y.toFloat(), mPaint)
    }

}