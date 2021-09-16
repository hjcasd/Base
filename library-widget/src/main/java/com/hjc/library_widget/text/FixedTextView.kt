package com.hjc.library_widget.text

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:31
 * @Description: 解决TextView默认上下的padding(对UI要求严格时使用)
 */
class FixedTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var mAdditionalPadding = 0

    init {
        includeFontPadding = false
    }

    override fun onDraw(canvas: Canvas) {
        val yOff = -mAdditionalPadding / 6
        canvas.translate(0f, yOff.toFloat())
        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        calculateAdditionalPadding()

        var newHeightMeasureSpec = heightMeasureSpec
        val mode = MeasureSpec.getMode(heightMeasureSpec)
        if (mode != MeasureSpec.EXACTLY) {
            val measureHeight = measureHeight(text.toString(), widthMeasureSpec)
            var height = measureHeight - mAdditionalPadding
            height += paddingTop + paddingBottom
            newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
        }
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec)
    }

    private fun measureHeight(text: String, widthMeasureSpec: Int): Int {
        val textSize = textSize
        val textView = TextView(context)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView.text = text
        textView.measure(widthMeasureSpec, 0)
        return textView.measuredHeight
    }

    private fun calculateAdditionalPadding() {
        val textSize = textSize
        val textView = TextView(context)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        textView.setLines(1)
        textView.measure(0, 0)
        val measuredHeight = textView.measuredHeight
        if (measuredHeight - textSize > 0) {
            mAdditionalPadding = (measuredHeight - textSize).toInt()
        }
    }
}