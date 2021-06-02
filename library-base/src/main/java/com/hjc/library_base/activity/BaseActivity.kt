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
import com.hjc.library_base.base.IBaseView
import com.hjc.library_base.base.IViewModelAction
import com.hjc.library_base.dialog.LoadingDialog
import com.hjc.library_base.loadsir.EmptyCallback
import com.hjc.library_base.loadsir.ErrorCallback
import com.hjc.library_base.loadsir.ProgressCallback
import com.hjc.library_base.loadsir.TimeoutCallback
import com.hjc.library_base.utils.ClickUtils
import com.hjc.library_base.viewmodel.BaseViewModel
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:09
 * @Description: Activity 基类
 */
abstract class BaseActivity<VDB : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity(),
    IBaseView, View.OnClickListener {

    // ViewDataBinding
    protected lateinit var mBindingView: VDB

    // ViewModel
    protected var mViewModel: VM? = null

    private var mLoadService: LoadService<*>? = null
    private var mLoadingDialog: LoadingDialog? = null


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
        if (mViewModel == null) {
            mViewModel = createViewModel()
        }

        mViewModel?.let {
            val viewModelAction: IViewModelAction = it
            viewModelAction.getActionLiveData().observe(this, { baseActionEvent: BaseActionEvent? ->
                baseActionEvent?.let { event ->
                    when (event.action) {
                        BaseActionEvent.SHOW_LOADING_DIALOG -> showLoading()

                        BaseActionEvent.DISMISS_LOADING_DIALOG -> dismissLoading()

                        BaseActionEvent.SHOW_PROGRESS -> showProgress()

                        BaseActionEvent.SHOW_CONTENT -> showContent()

                        BaseActionEvent.SHOW_EMPTY -> showEmpty()

                        BaseActionEvent.SHOW_ERROR -> showError()

                        BaseActionEvent.SHOW_TIMEOUT -> showTimeout()

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
     *
     * @param view 状态视图
     */
    fun initLoadSir(view: View?) {
        if (mLoadService == null) {
            mLoadService = LoadSir.getDefault().register(view) { v: View? -> onRetryBtnClick(v) }
        }
    }

    /**
     * 失败重试,重新加载事件
     */
    open fun onRetryBtnClick(v: View?) {}

    override fun showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog.newInstance()
        }
        mLoadingDialog?.showDialog(supportFragmentManager)
    }

    override fun dismissLoading() {
        mLoadingDialog?.let {
            val dialog = it.dialog
            if (dialog != null && dialog.isShowing) {
                it.dismiss()
            }
        }
    }

    override fun showContent() {
        mLoadService?.showSuccess()
    }

    override fun showProgress() {
        mLoadService?.showCallback(ProgressCallback::class.java)
    }

    override fun showEmpty() {
        mLoadService?.showCallback(EmptyCallback::class.java)
    }

    override fun showError() {
        mLoadService?.showCallback(ErrorCallback::class.java)
    }

    override fun showTimeout() {
        mLoadService?.showCallback(TimeoutCallback::class.java)
    }
}