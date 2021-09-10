package com.hjc.module_senior.widget.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.hjc.module_senior.R
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2019/10/31 11:47
 * @Description: 图片文字
 */
class MyCustom3View constructor(private val mContext: Context, attrs: AttributeSet?) : View(mContext, attrs) {

    private val mPicture = Picture()
    private var mWidth = 0
    private var mHeight = 0
    private var mType = 0

    init {
        recording()
    }

    /**
     * 录制内容，即将一些Canvas操作用Picture存储起来，录制的内容是不会直接显示在屏幕上的，只是存储起来了而已
     * 想要将Picture中的内容显示出来就需要手动调用播放(绘制),有如下3中方式绘制:
     * 1. 使用Picture提供的draw方法绘制。(不推荐使用)
     * 2. 使用Canvas提供的drawPicture方法绘制。
     * 3. 将Picture包装成为PictureDrawable，使用PictureDrawable的draw方法绘制。
     */
    private fun recording() {
        val paint = Paint()
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        paint.strokeWidth = 20f

        // 开始录制
        val canvas = mPicture.beginRecording(500, 500)
        canvas.translate(250f, 250f)
        canvas.drawCircle(0f, 0f, 100f, paint)
        // 结束录制
        mPicture.endRecording()
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
        // 设置画布颜色
        canvas.drawColor(ContextCompat.getColor(context, R.color.senior_green))
        when (mType) {
            1 -> drawPicture(canvas)
            2 -> drawBitmap(canvas)
            3 -> drawText(canvas)
            else -> {
            }
        }
    }

    /**
     * 矢量图
     */
    private fun drawPicture(canvas: Canvas) {
        // 将Picture中的内容绘制到canvas上
        canvas.drawPicture(
            mPicture, RectF(
                0f, 0f, mPicture.width.toFloat(), mPicture.height
                    .toFloat()
            )
        )

//                PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
//                pictureDrawable.setBounds(0, 0, mPicture.getWidth(), mPicture.getHeight());
//                pictureDrawable.draw(canvas);
    }

    /**
     * 位图
     */
    private fun drawBitmap(canvas: Canvas) {
        // 将坐标系移动到画布中央
        canvas.translate((mWidth shr 1).toFloat(), (mHeight shr 1).toFloat())
        val bitmap = getBitmap()
        //                canvas.drawBitmap(bitmap, new Matrix(), new Paint());

        // 指定图片绘制区域
        val src = Rect(0, 0, bitmap!!.width, bitmap.height)
        // 指定图片在屏幕上显示的区域
        val dst = Rect(0, 0, 200, 200)
        canvas.drawBitmap(bitmap, src, dst, Paint())
    }

    private fun getBitmap(): Bitmap?{
        var bitmap: Bitmap? = null
        try {
            val inputStream = mContext.assets.open("senior_bitmap.png")
            bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return bitmap
    }

    /**
     * 文字
     */
    private fun drawText(canvas: Canvas) {
        val text = "ABCDE"
        val paint = Paint()
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        paint.textSize = 50f
        paint.isAntiAlias = true

        // 绘制文字
        canvas.drawText(text, 200f, 200f, paint)

        // 绘制截取的指定文字(BC)
//        canvas.drawText(text, 1, 3, 200, 200, paint);
    }

    fun draw(type: Int) {
        mType = type
        invalidate()
    }
}