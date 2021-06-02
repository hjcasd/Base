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
import com.hjc.module_senior.databinding.SeniorActivityTouchCancelBinding

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 验证ACTION_CANCEL事件产生的时机
 */
@Route(path = RoutePath.Senior.URL_TOUCH_CANCEL)
class TouchCancelActivity : BaseActivity<SeniorActivityTouchCancelBinding, CommonViewModel>() {

    /*
     * 当在ScrollView中嵌套一个Button时,如果按下Button不放,并上下滑动后,
     * 当ScrollView滑动后会拦截事件,并会出现ACTION_CANCEL事件
     *
     * ACTION_CANCEL: 3
     *
     * ACTION_MOVE: 2
     *
     * ACTION_DOWN: 1
     *
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_touch_cancel
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
        LogUtils.e("TouchCancelActivity: dispatchTouchEvent")
        return super.dispatchTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        LogUtils.e("TouchCancelActivity: onTouchEvent")
        return super.onTouchEvent(event)
    }
}