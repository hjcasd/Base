package com.hjc.library_base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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
 * @Date: 2019/1/4 14:58
 * @Description: Fragment基类(用于懒加载)
 */
abstract class BaseLazyFragment<VDB : ViewDataBinding, VM : BaseViewModel> : Fragment(), View.OnClickListener {

    // ViewDataBinding
    protected lateinit var mBindingView: VDB

    // ViewModel
    protected var mViewModel: VM? = null

    // IStateView
    private var mStatusView: IStatusView? = null

    // ILoadingView
    private var mLoadingView: ILoadingView? = null

    // Fragment对应的Activity(避免使用getActivity()导致空指针异常)
    protected lateinit var mContext: Context

    /**
     * 判断View是否加载完成
     */
    private var isViewCreated = false

    /**
     * 判断当前Fragment是否可见
     */
    private var isFragmentVisible = false

    /**
     * 判断是否第一次加载数据
     */
    private var isFirstLoad = true


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        isFragmentVisible = isVisibleToUser
        lazyLoad()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBindingView = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        mBindingView.lifecycleOwner = this
        isViewCreated = true
        return mBindingView.root
    }

    /**
     * 获取布局的ID
     */
    abstract fun getLayoutId(): Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        lazyLoad()
    }

    private fun initViewModel() {
        ARouter.getInstance().inject(this)
        if (mViewModel == null) {
            mViewModel = createViewModel()
        }
        mViewModel?.let {
            val viewModelAction: IViewModelAction = it
            viewModelAction.getActionLiveData().observe(viewLifecycleOwner, { baseActionEvent: BaseActionEvent? ->
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
     * 懒加载
     */
    private fun lazyLoad() {
        if (isViewCreated && isFragmentVisible && isFirstLoad) {
            isFirstLoad = false
            initView()
            initData()
            observeLiveData()
            addListeners()
        }
    }

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
    protected open fun getImmersionBar(): ImmersionBar? {
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
    abstract fun initData()

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

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            getImmersionBar()?.init()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isViewCreated = false
    }

}