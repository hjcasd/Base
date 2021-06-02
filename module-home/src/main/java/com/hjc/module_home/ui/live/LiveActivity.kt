package com.hjc.module_home.ui.live

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeActivityLiveBinding
import com.hjc.module_home.viewmodel.live.LiveViewModel

/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: LiveData + ViewModel
 */
@Route(path = RoutePath.Home.URL_LIVE_DATA)
class LiveActivity : BaseActivity<HomeActivityLiveBinding, LiveViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_live
    }

    override fun createViewModel(): LiveViewModel {
        return ViewModelProvider(this).get(LiveViewModel::class.java)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mBindingView.liveViewModel = mViewModel
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
            R.id.btn_show -> mViewModel?.showData()
            R.id.btn_change -> mViewModel?.changeData()
            R.id.btn_hide -> mViewModel?.hideData()
        }
    }
}