package com.hjc.module_other.ui.video.view

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
 * @Description: 功能面板view
 */
class FunctionView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), View.OnClickListener {

    private lateinit var llRoot: LinearLayout
    private lateinit var llFunction: LinearLayout
    private lateinit var ivPlane: ImageView
    private lateinit var ivSeat: ImageView
    private lateinit var llEye: LinearLayout
    private lateinit var ivEye: ImageView

    private var flag = false

    private var mOnFunctionClickListener: OnFunctionClickListener? = null

    init {
        LayoutInflater.from(context).inflate(R.layout.other_layout_function, this)

        initView()
        addListener()
    }

    private fun initView() {
        llRoot = findViewById(R.id.ll_root)
        llFunction = findViewById(R.id.ll_function)
        ivPlane = findViewById(R.id.iv_plane)
        ivSeat = findViewById(R.id.iv_seat)
        llEye = findViewById(R.id.ll_eye)
        ivEye = findViewById(R.id.iv_eye)
    }

    private fun addListener() {
        ivPlane.setOnClickListener(this)
        ivSeat.setOnClickListener(this)
        llEye.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_plane -> {
                ivPlane.setImageResource(R.mipmap.other_icon_plane_red)
                ivSeat.setImageResource(R.mipmap.other_icon_seat_white)
                mOnFunctionClickListener?.onPlaneClick()
            }

            R.id.iv_seat -> {
                ivSeat.setImageResource(R.mipmap.other_icon_seat_red)
                ivPlane.setImageResource(R.mipmap.other_icon_plane_white)
                mOnFunctionClickListener?.onSeatClick()
            }

            R.id.ll_eye -> {
                if (flag) {
                    flag = false
                    show()
                    ivEye.setImageResource(R.mipmap.other_icon_eyes_red)
                    mOnFunctionClickListener?.onEyeStateChanged(0)
                } else {
                    flag = true
                    hide()
                    ivEye.setImageResource(R.mipmap.other_icon_eyes_white)
                    mOnFunctionClickListener?.onEyeStateChanged(1)
                }
            }

            else -> {
            }
        }
    }

    /**
     * 显示View
     */
    private fun show() {
        MediaViewUtils.showLeftView(llRoot)
    }

    /**
     * 隐藏View
     */
    private fun hide() {
        MediaViewUtils.hideLeftView(llRoot, llFunction.width.toFloat())
    }

    interface OnFunctionClickListener {
        fun onPlaneClick()
        fun onSeatClick()
        fun onEyeStateChanged(state: Int)
    }

    fun setOnFunctionClickListener(onFunctionClickListener: OnFunctionClickListener) {
        mOnFunctionClickListener = onFunctionClickListener
    }

}