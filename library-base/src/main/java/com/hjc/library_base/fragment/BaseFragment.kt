package com.hjc.library_base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.base.BaseActionEvent
import com.hjc.library_base.base.ILoadingView
import com.hjc.library_base.base.IStatusView
import com.hjc.library_base.base.IViewModelAction
import com.hjc.library_base.loadsir.BaseLoadingViewImpl
import com.hjc.library_base.loadsir.BaseStatusViewImpl
import com.hjc.library_base.viewmodel.BaseViewModel
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:58
 * @Description: Fragment基类
 */
abstract class BaseFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment(), View.OnClickListener {

    /**
     * Fragment对应的Activity(避免使用getActivity()导致空指针异常)
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

    /**
     * IStateView
     */
    private var mStatusView: IStatusView? = null

    /**
     * ILoadingView
     */
    private var mLoadingView: ILoadingView? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
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
        observeLiveData()
        addListeners()
    }

    /**
     * 初始化ViewModel
     */
    private fun initViewModel() {
        ARouter.getInstance().inject(this)
        if (mViewModel == null) {
            mViewModel = createViewModel()
        }
        mViewModel?.let {
            val viewModelAction: IViewModelAction = it
            viewModelAction.getActionLiveData().observe(viewLifecycleOwner) { baseActionEvent: BaseActionEvent? ->
                baseActionEvent?.let { event ->
                    when (event.action) {
                        BaseActionEvent.SHOW_LOADING_DIALOG -> mLoadingView?.showLoading()

                        BaseActionEvent.DISMISS_LOADING_DIALOG -> mLoadingView?.dismissLoading()

                        BaseActionEvent.SHOW_PROGRESS -> mStatusView?.showProgress()

                        BaseActionEvent.SHOW_CONTENT -> mStatusView?.showContent()

                        BaseActionEvent.SHOW_EMPTY -> mStatusView?.showEmpty(event.msg)

                        BaseActionEvent.SHOW_ERROR -> mStatusView?.showError(event.msg)

                        BaseActionEvent.SHOW_TIMEOUT -> mStatusView?.showTimeout()

                        else -> {
                        }
                    }
                }
            }
        }
    }

    /**
     * 创建ViewModel
     */
    abstract fun createViewModel(): VM?

    /**
     * 初始化View
     */
    open fun initView() {
        getImmersionBar()?.init()
        mLoadingView = createLoadingView()
        mStatusView = createStatusView()
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
        return BaseLoadingViewImpl(mContext)
    }

    /**
     * 初始化StatusView
     */
    open fun createStatusView(): IStatusView? {
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
        // ViewThrottleBindingAdapter已实现onClick事件防抖处理
        onSingleClick(v)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            getImmersionBar()?.init()
        }
    }

    /**
     * 注册LoadSir
     * @param view 绑定的View
     * @param listener 失败重试,重新加载事件
     */
    fun initLoadSir(view: View?, listener: Callback.OnReloadListener? = null) {
        mStatusView?.setLoadSir(view, listener)
    }

}