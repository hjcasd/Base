package com.hjc.module_senior.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.sin

/**
 * @Author: HJC
 * @Date: 2019/11/1 14:42
 * @Description: 自定义雷达图
 */
class RadarView constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // 数据个数
    private val count = 6

    // 角度
    private val angle = (Math.PI * 2 / count).toFloat()

    // 网格最大半径
    private var radius = 0f

    // 中心点X,Y坐标
    private var centerX = 0
    private var centerY = 0

    // 各维度标题
    private val titles = arrayOf("a", "b", "c", "d", "e", "f")

    // 各维度数值
    private val data = doubleArrayOf(100.0, 80.0, 60.0, 80.0, 100.0, 60.0)

    // 雷达区画笔
    private lateinit var mainPaint: Paint

    // 数据区画笔
    private lateinit var valuePaint: Paint

    // 文本画笔
    private lateinit var textPaint: Paint

    init {
        initPaint()
    }

    private fun initPaint() {
        mainPaint = Paint()
        mainPaint.style = Paint.Style.STROKE
        mainPaint.strokeWidth = 3f
        mainPaint.isAntiAlias = true
        mainPaint.color = Color.GRAY

        valuePaint = Paint()
        valuePaint.style = Paint.Style.FILL_AND_STROKE
        valuePaint.color = Color.BLUE
        valuePaint.isAntiAlias = true

        textPaint = Paint()
        textPaint.textSize = 40f
        textPaint.isAntiAlias = true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (w.coerceAtMost(h) shr 1) * 0.8f
        centerX = w / 2
        centerY = h / 2
        postInvalidate()
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        drawPolygon(canvas)
        drawLines(canvas)
        drawText(canvas)
        drawRegion(canvas)
    }

    /**
     * 绘制多边形
     *
     * @param canvas 画布
     */
    private fun drawPolygon(canvas: Canvas) {
        val path = Path()

        // 蜘蛛丝之间的间距
        val r = radius / (count - 1)
        for (i in 1 until count) {
            // 当前半径
            val curR = r * i
            path.reset()
            for (j in 0 until count) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY.toFloat())
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    val x = (centerX + curR * cos((angle * j).toDouble())).toFloat()
                    val y = (centerY + curR * sin((angle * j).toDouble())).toFloat()
                    path.lineTo(x, y)
                }
            }
            path.close()
            canvas.drawPath(path, mainPaint)
        }
    }

    /**
     * 绘制直线
     *
     * @param canvas 画布
     */
    private fun drawLines(canvas: Canvas) {
        val path = Path()
        for (i in 0 until count) {
            path.reset()
            path.moveTo(centerX.toFloat(), centerY.toFloat())
            val x = (centerX + radius * cos((angle * i).toDouble())).toFloat()
            val y = (centerY + radius * sin((angle * i).toDouble())).toFloat()
            path.lineTo(x, y)
            canvas.drawPath(path, mainPaint)
        }
    }

    /**
     * 绘制文字
     *
     * @param canvas 画布
     */
    private fun drawText(canvas: Canvas) {
        val fontMetrics = textPaint.fontMetrics
        val fontHeight = fontMetrics.descent - fontMetrics.ascent
        for (i in 0 until count) {
            val x =
                (centerX + (radius + fontHeight / 2) * cos((angle * i).toDouble())).toFloat()
            val y =
                (centerY + (radius + fontHeight / 2) * sin((angle * i).toDouble())).toFloat()
            if (angle * i >= 0 && angle * i <= Math.PI / 2) {
                // 第4象限
                canvas.drawText(titles[i], x, y, textPaint)
            } else if (angle * i >= 3 * Math.PI / 2 && angle * i <= Math.PI * 2) {
                // 第3象限
                canvas.drawText(titles[i], x, y, textPaint)
            } else if (angle * i > Math.PI / 2 && angle * i <= Math.PI) {
                // 第2象限
                val dis = textPaint.measureText(titles[i])
                canvas.drawText(titles[i], x - dis, y, textPaint)
            } else if (angle * i >= Math.PI && angle * i < 3 * Math.PI / 2) {
                // 第1象限
                val dis = textPaint.measureText(titles[i])
                canvas.drawText(titles[i], x - dis, y, textPaint)
            }
        }
    }

    /**
     * 绘制区域
     *
     * @param canvas 画布
     */
    private fun drawRegion(canvas: Canvas) {
        val path = Path()
        valuePaint.alpha = 255
        for (i in 0 until count) {
            // 数据最大值
            val maxValue = 100f
            val percent = data[i] / maxValue
            val x = (centerX + radius * cos((angle * i).toDouble()) * percent).toFloat()
            val y = (centerY + radius * sin((angle * i).toDouble()) * percent).toFloat()
            if (i == 0) {
                path.moveTo(x, centerY.toFloat())
            } else {
                path.lineTo(x, y)
            }
            //绘制小圆点
            canvas.drawCircle(x, y, 10f, valuePaint)
        }
        valuePaint.style = Paint.Style.STROKE
        canvas.drawPath(path, valuePaint)
        valuePaint.alpha = 127
        //绘制填充区域
        valuePaint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path, valuePaint)
    }
}