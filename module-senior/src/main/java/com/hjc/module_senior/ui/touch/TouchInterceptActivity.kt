package com.hjc.module_senior.ui.touch

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityTouchInterceptBinding

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 验证事件拦截逻辑
 */
@Route(path = RoutePath.Senior.URL_TOUCH_INTERCEPT)
class TouchInterceptActivity : BaseActivity<SeniorActivityTouchInterceptBinding, CommonViewModel>() {

    /*
     * 重写ViewGroup的onInterceptTouchEvent(ev),直接返回true,表示拦截事件,不会将事件传递给子View
     * 重写ViewGroup的onTouchEvent(ev),直接返回true,表示将事件交给onTouchEvent(ev)来消费
     *
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_touch_intercept
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
            override fun leftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        LogUtils.e("TouchInterceptActivity: dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("TouchInterceptActivity: onTouchEvent")
        return super.onTouchEvent(event)
    }
}