package com.hjc.module_senior.ui.touch

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityTouchDefaultBinding

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 验证事件默认的传递流程
 */
@Route(path = RouteSeniorPath.URL_TOUCH_DEFAULT)
class TouchDefaultActivity : BaseActivity<SeniorActivityTouchDefaultBinding, CommonViewModel>() {

    /*
     * 事件默认传递流程:
     *
     * ACTION_DOWN:
     * Activity: dispatchTouchEvent -> MyViewGroup: dispatchTouchEvent -> MyViewGroup: onInterceptTouchEvent(默认返回false,即不拦截事件,将事件分发给子View)
     * -> MyView: dispatchTouchEvent -> MyView: onTouchEvent(默认返回false,即不消费事件) -> MyViewGroup: onTouchEvent(默认返回false,即不消费事件)
     * -> Activity: onTouchEvent
     *
     * ACTION_MOVE:
     * Activity: dispatchTouchEvent -> Activity: onTouchEvent
     *
     *
     * 注意: 同一事件序列(例如手指按下后的ACTION_DOWN和ACTION_MOVE即为一个事件序列),如果子View(包括ViewGroup)没有消费该事件,那么后续的事件不会再传递到子View中
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_touch_default
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.senior_color)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.e("TouchDefaultActivity: dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("TouchDefaultActivity: onTouchEvent")
        return super.onTouchEvent(event)
    }
}