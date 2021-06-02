package com.hjc.module_main.ui.splash

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.RoutePath
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.module_main.R
import com.hjc.module_main.databinding.MainActivitySplashBinding
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2021/1/8 9:44
 * @Description: 启动页
 */
@Route(path = RoutePath.Main.URL_SPLASH)
class SplashActivity : BaseActivity<MainActivitySplashBinding, CommonViewModel>() {

    private var disposable1: Disposable? = null
    private var disposable2: Disposable? = null

    override fun getLayoutId(): Int {
        return R.layout.main_activity_splash
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this).titleBar(mBindingView.llTime)
    }

    override fun initData(savedInstanceState: Bundle?) {
        disposable1 = Observable.timer(3, TimeUnit.SECONDS)
            .compose(RxSchedulers.ioToMain())
            .subscribe { toMain() }

        //倒计时3s
        disposable2 = Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS)
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
        RouteManager.jump(RoutePath.Main.URL_MAIN)
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