package com.hjc.library_base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.utils.ClickUtils
import com.hjc.library_base.viewmodel.BaseViewModel

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: (含有Fragment)Activity 基类
 */
abstract class BaseFragmentActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), View.OnClickListener {

    // ViewDataBinding
    protected lateinit var mBindingView: VDB

    // ViewModel
    protected var mViewModel: VM? = null

    private var mCurrentFragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBindingView = DataBindingUtil.setContentView(this, getLayoutId())
        mBindingView.lifecycleOwner = this

        initViewModel()
        initView()
        initData(savedInstanceState)
        observeLiveData()
        addListeners()
    }

    /**
     * 获取布局的ID
     */
    abstract fun getLayoutId(): Int

    private fun initViewModel() {
        mViewModel = createViewModel()
    }

    /**
     * 获取viewModel
     */
    abstract fun createViewModel(): VM?

    /**
     * 初始化View
     */
    open fun initView() {
        getImmersionBar()?.init()
        ARouter.getInstance().inject(this)
    }

    /**
     * 初始化沉浸式
     */
    open fun getImmersionBar(): ImmersionBar? {
        return null
    }

    /**
     * 初始化数据
     */
    abstract fun initData(savedInstanceState: Bundle?)

    /**
     * 监听LiveData
     */
    open fun observeLiveData() {}

    /**
     * 设置监听器
     */
    abstract fun addListeners()

    /**
     * 设置点击事件
     */
    abstract fun onSingleClick(v: View?)

    /**
     * 布局中Fragment的容器ID
     */
    abstract fun getFragmentContentId(): Int

    override fun onClick(v: View) {
        //避免快速点击
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!")
            return
        }
        onSingleClick(v)
    }

    /**
     * 显示fragment
     */
    fun showFragment(fragment: Fragment) {
        if (mCurrentFragment !== fragment) {
            val fm = supportFragmentManager
            val ft = fm.beginTransaction()
            ft.hide(mCurrentFragment)
            mCurrentFragment = fragment
            if (!fragment.isAdded) {
                ft.add(getFragmentContentId(), fragment).show(fragment).commit()
            } else {
                ft.show(fragment).commit()
            }
        }
    }
}