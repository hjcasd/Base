package com.hjc.library_base.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.base.BaseActionEvent
import com.hjc.library_base.base.IViewModelAction
import com.hjc.library_base.utils.ClickUtils
import com.hjc.library_base.view.ILoadingView
import com.hjc.library_base.view.IStatusView
import com.hjc.library_base.view.impl.BaseLoadingViewImpl
import com.hjc.library_base.view.impl.BaseStatusViewImpl
import com.hjc.library_base.viewmodel.BaseViewModel

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: Activity 基类
 */
abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(), View.OnClickListener {

    // ViewDataBinding
    protected lateinit var mBindingView: VDB

    // ViewModel
    protected var mViewModel: VM? = null

    // IStateView
    private var mStatusView: IStatusView? = null

    // ILoadingView
    private var mLoadingView: ILoadingView? = null

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

        mViewModel?.let {
            val viewModelAction: IViewModelAction = it
            viewModelAction.getActionLiveData().observe(this, { baseActionEvent: BaseActionEvent? ->
                baseActionEvent?.let { event ->
                    when (event.action) {
                        BaseActionEvent.SHOW_LOADING_DIALOG -> mLoadingView?.showLoading()

                        BaseActionEvent.DISMISS_LOADING_DIALOG -> mLoadingView?.dismissLoading()

                        BaseActionEvent.SHOW_PROGRESS -> mStatusView?.showProgress()

                        BaseActionEvent.SHOW_CONTENT -> mStatusView?.showContent()

                        BaseActionEvent.SHOW_EMPTY -> mStatusView?.showEmpty()

                        BaseActionEvent.SHOW_ERROR -> mStatusView?.showError()

                        BaseActionEvent.SHOW_TIMEOUT -> mStatusView?.showTimeout()

                        else -> {
                        }
                    }
                }
            })
        }
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
        mLoadingView = createLoadingView()
        mStatusView = createStatusView()
        ARouter.getInstance().inject(this)
    }

    /**
     * 初始化沉浸式
     */
    open fun getImmersionBar(): ImmersionBar? {
        return null
    }

    /**
     * 初始化Loading
     */
    open fun createLoadingView(): ILoadingView? {
        return BaseLoadingViewImpl(this)
    }

    /**
     * 初始化StatusView
     */
    open fun createStatusView(): IStatusView {
        return BaseStatusViewImpl()
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

    override fun onClick(v: View) {
        //避免快速点击
        if (ClickUtils.isFastClick()) {
            ToastUtils.showShort("点的太快了,歇会呗!")
            return
        }
        onSingleClick(v)
    }

    /**
     * 注册LoadSir
     * @param view 绑定的View
     */
    fun initLoadSir(view: View?) {
        mStatusView?.setLoadSir(view) { v: View? -> onRetryBtnClick(v) }
    }

    /**
     * 失败重试,重新加载事件
     */
    open fun onRetryBtnClick(v: View?) {}
}