package com.hjc.library_base.dialog

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.*
import androidx.annotation.StyleRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.utils.ClickUtils.isFastClick
import com.hjc.library_base.viewmodel.BaseViewModel

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: DialogFragment基类
 */
abstract class BaseFragmentDialog<VDB : ViewDataBinding, VM : BaseViewModel> : DialogFragment(),
    View.OnClickListener {

    // ViewDataBinding
    protected lateinit var mBindingView: VDB

    // ViewModel
    protected var mViewModel: VM? = null

    protected lateinit var mContext: Context

    private var mGravity = Gravity.CENTER //位置
    private var mAnimStyle = 0 //进入退出动画

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, getStyleRes())
    }

    /**
     * 获取Dialog的Theme
     */
    abstract fun getStyleRes(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBindingView = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBindingView.lifecycleOwner = this
        return mBindingView.root
    }

    /**
     * 获取布局的ID
     */
    abstract fun getLayoutId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initData(savedInstanceState)
        addListeners()
    }

    private fun initViewModel() {
        if (mViewModel == null) {
            mViewModel = createViewModel()
        }
    }

    /**
     * 获取viewModel
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
    protected abstract fun addListeners()

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        dialog?.let {
            val window = it.window
            if (window != null) {
                val params = window.attributes
                params.gravity = mGravity
                params.width = getWidth()
                params.height = getHeight()

                //设置dialog动画
                if (mAnimStyle != 0) {
                    window.setWindowAnimations(mAnimStyle)
                }
                window.attributes = params
            }
        }
    }

    /**
     * 设置宽度为屏幕宽度的0.8
     */
    open fun getWidth(): Int {
        val wm = mContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width = point.x
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
    fun setGravity(gravity: Int): BaseFragmentDialog<*, *> {
        mGravity = gravity
        return this
    }

    /**
     * 设置动画类型
     */
    fun setAnimStyle(@StyleRes animStyle: Int): BaseFragmentDialog<*, *> {
        mAnimStyle = animStyle
        return this
    }

    /**
     * 显示Fragment
     */
    fun showDialog(fm: FragmentManager) {
        show(fm, "DialogFragment")
    }

    /**
     * 设置点击事件
     */
    abstract fun onSingleClick(v: View?)

    override fun onClick(v: View) {
        //避免快速点击
        if (isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!")
            return
        }
        onSingleClick(v)
    }
}