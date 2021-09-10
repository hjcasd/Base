package com.hjc.module_senior.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.hjc.module_senior.R

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:48
 * @Description: 基本图形
 */
class MyCustom1View constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var mPaint: Paint
    private lateinit var mAlisPaint: Paint

    private var mType = 0

    private lateinit var rectF1: RectF
    private lateinit var rectF2: RectF
    private lateinit var rectF3: RectF

    init {
        initPaint()
    }

    private fun initPaint() {
        mPaint = Paint()
        // 画笔颜色
        mPaint.color = Color.BLACK
        // 画笔填充模式
        mPaint.style = Paint.Style.FILL
        // 描边宽度
        mPaint.strokeWidth = 20f
        // 抗锯齿
        mPaint.isAntiAlias = true

        mAlisPaint = Paint()
        mAlisPaint.color = Color.BLUE
        mAlisPaint.style = Paint.Style.FILL
        mAlisPaint.strokeWidth = 10f

        rectF1 = RectF(100f, 100f, 400f, 300f)
        rectF2 = RectF(100f, 100f, 400f, 300f)
        rectF3 = RectF(100f, 100f, 400f, 400f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 设置画布颜色
        canvas.drawColor(ContextCompat.getColor(context, R.color.senior_green))

        // 画X轴
        canvas.drawLine(0f, 0f, width.toFloat(), 0f, mAlisPaint)

        // 画Y轴
        canvas.drawLine(0f, 0f, 0f, height.toFloat(), mAlisPaint)

        when (mType) {
            1 -> {
                // 画一个点: (100, 100)
                canvas.drawPoint(100f, 100f, mPaint)
            }
            2 -> {
                // 画多个点: (200, 200),(300, 300),(400, 400)
                val points1 = floatArrayOf(200f, 200f, 300f, 300f, 400f, 400f)
                canvas.drawPoints(points1, mPaint)
            }
            3 -> {
                // 画一条直线: (200, 200) -> (400, 400)
                canvas.drawLine(200f, 200f, 400f, 400f, mPaint)
            }
            4 -> {
                // 画多条直线: (100, 100) -> (200, 200), (100, 300) -> (200, 400)
                val points2 = floatArrayOf(100f, 100f, 200f, 200f, 100f, 300f, 200f, 400f)
                canvas.drawLines(points2, mPaint)
            }
            5 -> {
                // 画矩形: 左上(100, 100), 右下(400, 300)
                canvas.drawRect(100f, 100f, 400f, 300f, mPaint)
            }
            6 -> {
                // rx, ry为椭圆的两个半径(一般一样大小)
                canvas.drawRoundRect(rectF1, 30f, 30f, mPaint)
            }

            7 -> {
                // 画椭圆: 左上(100, 100), 右下(400, 300)矩形的内切图形
                canvas.drawOval(rectF2, mPaint)
            }
            8 -> {
                // 画圆: 圆心(200, 200),半径150
                canvas.drawCircle(200f, 200f, 150f, mPaint)
            }
            9 -> {
                // 画圆弧: 左上(100, 100), 右下(400, 400)的矩形中画弧, 角度为90, 使用中心点
                canvas.drawArc(rectF3, 0f, 90f, true, mPaint)
            }
            else -> {
            }
        }
    }

    fun draw(type: Int) {
        mType = type
        invalidate()
    }
}