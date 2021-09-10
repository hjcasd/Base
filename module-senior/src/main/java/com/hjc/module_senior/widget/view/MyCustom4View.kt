package com.hjc.module_senior.widget.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.hjc.module_senior.R

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:47
 * @Description: Path操作
 */
class MyCustom4View constructor(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var mPaint: Paint
    private lateinit var mAlisPaint: Paint

    private var mWidth = 0
    private var mHeight = 0
    private var mType = 0

    init {
        initPaint()
    }

    private fun initPaint() {
        mPaint = Paint()
        // 画笔颜色
        mPaint.color = Color.BLACK
        // 画笔填充模式
        mPaint.style = Paint.Style.STROKE
        // 描边宽度
        mPaint.strokeWidth = 4f
        // 抗锯齿
        mPaint.isAntiAlias = true

        mAlisPaint = Paint()
        mAlisPaint.color = Color.WHITE
        mAlisPaint.style = Paint.Style.FILL
        mAlisPaint.strokeWidth = 4f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        mWidth = width
        mHeight = height
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(ContextCompat.getColor(context, R.color.senior_green))
        canvas.translate((mWidth shr 1).toFloat(), (mHeight shr 1).toFloat())
        val points = floatArrayOf(
            -mWidth.toFloat(),
            0f,
            mWidth.toFloat(),
            0f,
            0f,
            -mHeight.toFloat(),
            0f,
            mHeight.toFloat()
        )
        canvas.drawLines(points, mAlisPaint)
        when (mType) {
            1 -> drawView1(canvas)
            2 -> drawView2(canvas)
            3 -> drawView3(canvas)
            4 -> drawView4(canvas)
            else -> {
            }
        }
    }

    /**
     * lineTo(), moveTo(), setLastPoint(), close()
     */
    private fun drawView1(canvas: Canvas) {
        val path = Path()
        // 从上一次点(默认原点)到指定点间画一条直线
        path.lineTo(200f, 200f)

        // 移动下一次操作的起点位置(将起点移动到(200, 100))
//        path.moveTo(200, 100);

        // 重置上一次操作的最后一个点(将最后一个点由(200, 200)变为(200, 100))
//        path.setLastPoint(200, 100);
        path.lineTo(200f, 0f)

        // 连接当前最后一个点和最初的一个点(如果两个点不重合的话)，最终形成一个封闭的图形
        path.close()
        canvas.drawPath(path, mPaint)
    }

    /**
     * 基本形状
     */
    private fun drawView2(canvas: Canvas) {
        val path = Path()
        // 添加矩形,cw表示顺时针,ccw表示逆时针
        path.addRect(-200f, -200f, 200f, 200f, Path.Direction.CW)
        path.setLastPoint(-300f, 300f)
        canvas.drawPath(path, mPaint)
    }

    /**
     * Path
     */
    private fun drawView3(canvas: Canvas) {
        // 翻转y坐标轴
        canvas.scale(1f, -1f)
        val path = Path()
        val src = Path()
        path.addRect(-200f, -200f, 200f, 200f, Path.Direction.CW)
        src.addCircle(0f, 0f, 100f, Path.Direction.CW)
        path.addPath(src, 0f, 200f)
        canvas.drawPath(path, mPaint)
    }

    /**
     * addArc(), arcTo
     */
    private fun drawView4(canvas: Canvas) {
        canvas.scale(1f, -1f)
        val path = Path()
        path.lineTo(100f, 100f)
        val oval = RectF(0f, 0f, 300f, 300f)
        // 直接添加一个圆弧到path中
//        path.addArc(oval, 0, 270);

        // 添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
        path.arcTo(oval, 0f, 270f)
        canvas.drawPath(path, mPaint)
    }

    fun draw(type: Int) {
        mType = type
        invalidate()
    }
}