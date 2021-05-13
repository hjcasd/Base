package com.hjc.base.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.base.R
import com.hjc.base.constant.RoutePath
import com.hjc.base.databinding.ActivityLoginBinding
import com.hjc.base.viewmodel.login.LoginViewModel
import com.hjc.baselib.activity.BaseActivity
import com.hjc.baselib.widget.bar.OnViewLeftClickListener

/**
 * @Author: HJC
 * @Date: 2021/1/8 11:29
 * @Description: 登录
 */
@Route(path = RoutePath.URL_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.app_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mBindingView.loginViewModel = mViewModel
    }

    override fun observeLiveData() {
        super.observeLiveData()

        mViewModel?.run {
            getLoginData().observe(this@LoginActivity) { result ->
                LogUtils.e("result: ${result.username}")
                mBindingView.etPhone.clearFocus()
                mBindingView.etCode.clearFocus()
            }
        }
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {

            override fun leftClick(view: View?) {
                finish()
            }

        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn_login -> mViewModel?.login()
        }
    }

}