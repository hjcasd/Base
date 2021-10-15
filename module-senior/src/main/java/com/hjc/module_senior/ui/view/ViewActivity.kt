package com.hjc.module_senior.ui.view

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.path.RouteSeniorPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_senior.R
import com.hjc.module_senior.databinding.SeniorActivityViewBinding

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义View
 */
@Route(path = RouteSeniorPath.URL_VIEW)
class ViewActivity : BaseActivity<SeniorActivityViewBinding, CommonViewModel>() {

    /**
     * 1. 定义自定义的View的属性(res/value/attrs.xml, string,color,dimension,integer,enum,reference,float,boolean,fraction,flag)
     * 2. 在View的构造方法中获取自定义的属性
     * 3. 重写onMeasure()方法()
     * 4. 重写onDraw()方法
     */

    override fun getLayoutId(): Int {
        return R.layout.senior_activity_view
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
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn_base -> RouteManager.jump(RouteSeniorPath.URL_VIEW_BASE)

            R.id.btn_canvas -> RouteManager.jump(RouteSeniorPath.URL_VIEW_CANVAS)

            R.id.btn_picture -> RouteManager.jump(RouteSeniorPath.URL_VIEW_PICTURE)

            R.id.btn_path -> RouteManager.jump(RouteSeniorPath.URL_VIEW_PATH)

            R.id.btn_radar -> RouteManager.jump(RouteSeniorPath.URL_VIEW_RADAR)

            R.id.btn_test -> RouteManager.jump(RouteSeniorPath.URL_VIEW_TEST)

            else -> {
            }
        }
    }
}