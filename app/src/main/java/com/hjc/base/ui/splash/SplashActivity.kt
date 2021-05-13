package com.hjc.base.ui.splash

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.base.R
import com.hjc.base.constant.RoutePath
import com.hjc.base.databinding.ActivitySplashBinding
import com.hjc.base.utils.RxSchedulers
import com.hjc.base.utils.helper.RouteManager
import com.hjc.baselib.activity.BaseActivity
import com.hjc.baselib.viewmodel.CommonViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2021/1/8 9:44
 * @Description: 启动页
 */
@Route(path = RoutePath.URL_SPLASH)
class SplashActivity : BaseActivity<ActivitySplashBinding, CommonViewModel>() {

    private var disposable1: Disposable? = null
    private var disposable2: Disposable? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this).fullScreen(true)
    }

    override fun initData(savedInstanceState: Bundle?) {
        disposable1 = Observable.timer(3, TimeUnit.SECONDS)
            .compose(RxSchedulers.ioToMain())
            .subscribe { toMain() }

        //倒计时3s
        disposable2 = Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
            .compose(RxSchedulers.ioToMain())
            .subscribe { aLong ->
                val time = "倒计时" + (3 - aLong) + "s"
                mBindingView.tvTime.text = time
            }
    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.tv_time -> toMain()
            else -> {
            }
        }
    }

    private fun toMain() {
        RouteManager.jump(RoutePath.URL_MAIN)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    private fun dispose() {
        disposable1?.dispose()
        disposable2?.dispose()
    }

}