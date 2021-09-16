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
 * @Description: 画布操作
 */
class MyCustom2View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mPaint: Paint = Paint()

    private var mType = 0
    private var mWidth = 0
    private var mHeight = 0

    init {
        initPaint()
    }

    private fun initPaint() {
        // 画笔颜色
        mPaint.color = Color.BLACK
        // 画笔填充模式
        mPaint.style = Paint.Style.FILL
        // 描边宽度
        mPaint.strokeWidth = 20f
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = View.MeasureSpec.getSize(widthMeasureSpec)
        val height = View.MeasureSpec.getSize(heightMeasureSpec)
        mWidth = width
        mHeight = height
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 设置画布颜色
        canvas.drawColor(ContextCompat.getColor(context, R.color.senior_green))
        when (mType) {
            1 -> translate(canvas)
            2 -> scale(canvas)
            3 -> rotate(canvas)
            else -> {
            }
        }
    }

    private fun translate(canvas: Canvas) {
        // 位移操作(位移是基于当前位置移动，而不是每次基于屏幕左上角的(0,0)点移动)
        // 将原点坐标由(0,0)移动到(200, 200)
        mPaint.color = Color.BLACK
        canvas.translate(200f, 200f)
        canvas.drawCircle(0f, 0f, 100f, mPaint)

        // 位移叠加
        mPaint.color = Color.BLUE

        // 将原点坐标由(200,200)移动到(400, 400)
        canvas.translate(200f, 200f)
        canvas.drawCircle(0f, 0f, 100f, mPaint)
    }

    private fun scale(canvas: Canvas) {
        canvas.translate((mWidth shr 1).toFloat(), (mHeight shr 1).toFloat())
        mPaint.color = Color.BLACK
        val rectF = RectF(0f, -200f, 200f, 0f)
        canvas.drawRect(rectF, mPaint)

        //按原点进行缩放
        canvas.scale(0.5f, 0.5f)
        //        canvas.scale(-0.5f, -0.5f);          //按原点进行缩放(如果为负数,则先缩放,然后按中心轴翻转)
//        canvas.scale(0.5f, 0.5f, 200, 0);    //缩放中心由原点变为(200, 0)进行缩放
//        canvas.scale(-0.5f, -0.5f, 200, 0);    //缩放中心由原点变为(200, 0)进行缩放,同时按中心轴翻转

//        canvas.scale(0.5f, 0.5f);   //缩放叠加
//        canvas.scale(0.5f, 0.5f);   // x轴实际缩放为0.5x0.5=0.25 y轴实际缩放为0.5x0.5=0.25
        mPaint.color = Color.BLUE
        canvas.drawRect(rectF, mPaint)
    }

    private fun rotate(canvas: Canvas) {
        canvas.translate((mWidth shr 1).toFloat(), (mHeight shr 1).toFloat())
        mPaint.color = Color.BLACK
        val rectF = RectF(0f, -200f, 200f, 0f)
        canvas.drawRect(rectF, mPaint)

//        canvas.rotate(180);        //旋转180,默认以原点旋转
//        canvas.rotate(180, 200, 0);  //以(200, 0)旋转180度
        canvas.rotate(90f) //旋转叠加
        canvas.rotate(90f) //旋转180
        mPaint.color = Color.BLUE
        canvas.drawRect(rectF, mPaint)
    }

    fun draw(type: Int) {
        mType = type
        invalidate()
    }
}