package com.hjc.module_login.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteLoginPath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_login.R
import com.hjc.module_login.databinding.LoginActivityBinding
import com.hjc.module_login.viewmodel.LoginViewModel

/**
 * @Author: HJC
 * @Date: 2021/1/8 11:29
 * @Description: 登录页面
 */
@Route(path = RouteLoginPath.URL_LOGIN_ACTIVITY)
class LoginActivity : BaseActivity<LoginActivityBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.login_activity
    }

    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.login_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mBindingView.loginViewModel = mViewModel
    }

    override fun observeLiveData() {
        super.observeLiveData()

        mViewModel?.run {
            loginData.observe(this@LoginActivity) { response ->
                LogUtils.e("result: ${response?.username}")
                mBindingView.etPhone.clearFocus()
                mBindingView.etCode.clearFocus()
            }
        }
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
            R.id.btn_login -> mViewModel?.login()
        }
    }

    override fun finish() {
        super.finish()
        //重写Activity动画
        overridePendingTransition(R.anim.common_login_close_enter, R.anim.common_login_close_exit)
    }

}