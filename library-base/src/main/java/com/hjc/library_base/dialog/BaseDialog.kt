package com.hjc.library_base.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.R
import com.hjc.library_base.utils.ClickUtils
import com.hjc.library_base.viewmodel.BaseViewModel

/**
 * @Author: HJC
 * @Date: 2021/6/20 17:29
 * @Description: Dialog基类
 */
abstract class BaseDialog<VDB : ViewDataBinding, VM : BaseViewModel> constructor(
    private val mContext: Context, themeResId: Int = R.style.Base_Dialog
) : Dialog(mContext, themeResId), View.OnClickListener {

    /**
     * ViewDataBinding
     */
    protected lateinit var mBindingView: VDB

    /**
     * ViewModel
     */
    protected var mViewModel: VM? = null

    /**
     * dialog的位置
     */
    private var mGravity = Gravity.CENTER


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBindingView = DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayoutId(), null, false)
        setContentView(mBindingView.root)

        window?.let {
            val params = it.attributes
            params.gravity = mGravity
            params.width = getWidth()
            params.height = getHeight()
            it.attributes = params
        }
        initViewModel()
        initView()
        initData(savedInstanceState)
        addListeners()
    }

    /**
     * 获取布局的ID
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        if (mViewModel == null) {
            mViewModel = createViewModel()
        }
    }

    /**
     * 创建ViewModel
     */
    abstract fun createViewModel(): VM?

    /**
     * 初始化View
     */
    open fun initView() {}

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 设置监听器
     */
    abstract fun addListeners()

    /**
     * 设置点击事件
     */
    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View) {
        //避免快速点击
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!")
            return
        }
        onSingleClick(v)
    }

    /**
     * 设置宽度为屏幕宽度的0.8
     */
    open fun getWidth(): Int {
        val dm: DisplayMetrics = mContext.resources.displayMetrics
        val width = dm.widthPixels
        return (width * 0.8).toInt()
    }

    /**
     * 设置高度wrap
     */
    open fun getHeight(): Int {
        return WindowManager.LayoutParams.WRAP_CONTENT
    }

    /**
     * 设置Dialog位置
     */
    fun setGravity(gravity: Int): BaseDialog<*, *> {
        mGravity = gravity
        return this
    }

}