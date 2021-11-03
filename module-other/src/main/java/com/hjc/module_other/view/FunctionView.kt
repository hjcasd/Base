package com.hjc.module_other.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.hjc.module_other.R
import com.hjc.module_other.utils.MediaViewUtils

/**
 * @Author: HJC
 * @Date: 2021/9/27 10:55
 * @Description: view
 */
class FunctionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private var llRoot: LinearLayout
    private var llFunction: LinearLayout
    private var ivPlane: ImageView
    private var ivSeat: ImageView
    private var llEye: LinearLayout
    private var ivEye: ImageView

    private var flag = false

    private var mOnFunctionClickListener: OnFunctionClickListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.other_layout_function, this)
        llRoot = findViewById(R.id.ll_root)
        llFunction = findViewById(R.id.ll_function)
        ivPlane = findViewById(R.id.iv_plane)
        ivSeat = findViewById(R.id.iv_seat)
        llEye = findViewById(R.id.ll_eye)
        ivEye = findViewById(R.id.iv_eye)

        ivPlane.setOnClickListener(this)
        ivSeat.setOnClickListener(this)
        llEye.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_plane -> {
                ivPlane.setImageResource(R.mipmap.other_icon_plane_red)
                ivSeat.setImageResource(R.mipmap.other_icon_seat_white)
                mOnFunctionClickListener?.onPlaneClick(this)
            }

            R.id.iv_seat -> {
                ivSeat.setImageResource(R.mipmap.other_icon_seat_red)
                ivPlane.setImageResource(R.mipmap.other_icon_plane_white)
                mOnFunctionClickListener?.onSeatClick(this)
            }

            R.id.ll_eye -> {
                if (flag) {
                    flag = false
                    ivEye.setImageResource(R.mipmap.other_icon_eyes_red)
                    MediaViewUtils.showLeftView(llRoot)
                } else {
                    flag = true
                    ivEye.setImageResource(R.mipmap.other_icon_eyes_white)
                    MediaViewUtils.hideLeftView(llRoot, llFunction.width.toFloat())
                }
            }

            else -> {
            }
        }
    }

    interface OnFunctionClickListener {
        fun onPlaneClick(view: View?)
        fun onSeatClick(view: View?)
    }

    fun setOnFunctionClickListener(onFunctionClickListener: OnFunctionClickListener) {
        mOnFunctionClickListener = onFunctionClickListener
    }

}