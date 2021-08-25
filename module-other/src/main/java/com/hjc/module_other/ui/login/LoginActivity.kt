package com.hjc.module_other.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityLoginBinding
import com.hjc.module_other.viewmodel.LoginViewModel

/**
 * @Author: HJC
 * @Date: 2021/1/8 11:29
 * @Description: 登录
 */
@Route(path = RouteOtherPath.URL_LOGIN)
class LoginActivity : BaseActivity<OtherActivityLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.other_activity_login
    }

    override fun createViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.other_color)
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

    override fun finish() {
        super.finish()
        //重写Activity动画
        overridePendingTransition(R.anim.other_login_close_enter, R.anim.other_login_close_exit)
    }

}