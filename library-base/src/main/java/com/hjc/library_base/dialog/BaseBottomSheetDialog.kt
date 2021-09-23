package com.hjc.library_base.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentManager
import com.blankj.utilcode.util.ToastUtils
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hjc.library_base.utils.ClickUtils.isFastClick
import com.hjc.library_base.viewmodel.BaseViewModel

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: BottomSheetDialogFragment基类
 */
abstract class BaseBottomSheetDialog<VDB : ViewDataBinding, VM : BaseViewModel> : BottomSheetDialogFragment(),
    View.OnClickListener {

    /**
     * 上下文
     */
    protected lateinit var mContext: Context

    /**
     * ViewDataBinding
     */
    protected lateinit var mBindingView: VDB

    /**
     * ViewModel
     */
    protected var mViewModel: VM? = null


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
    @StyleRes
    abstract fun getStyleRes(): Int

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        dialog?.let {
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.delegate.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.run {
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT  //自定义高度
            }

            val v = view
            v?.post {
                val parent = v.parent as View
                val params = parent.layoutParams as CoordinatorLayout.LayoutParams
                val bottomSheetBehavior = params.behavior as BottomSheetBehavior<*>?
                bottomSheetBehavior?.run { peekHeight = v.measuredHeight }
            }
        }
    }

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
    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        initData(savedInstanceState)
        addListeners()
    }

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
        if (isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!")
            return
        }
        onSingleClick(v)
    }

    /**
     * 显示Fragment
     */
    fun showDialog(fm: FragmentManager) {
        show(fm, "DialogFragment")
    }

}