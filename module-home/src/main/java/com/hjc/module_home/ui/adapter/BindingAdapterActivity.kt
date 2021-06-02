package com.hjc.module_home.ui.adapter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.RoutePath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_home.R
import com.hjc.module_home.databinding.HomeActivityAdapterBinding
import com.hjc.module_home.viewmodel.adapter.AdapterViewModel

/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: BindingAdapter
 */
@Route(path = RoutePath.Home.URL_BINDING_ADAPTER)
class BindingAdapterActivity : BaseActivity<HomeActivityAdapterBinding, AdapterViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.home_activity_adapter
    }

    override fun createViewModel(): AdapterViewModel {
        return ViewModelProvider(this).get(AdapterViewModel::class.java)
    }

    override fun initData(savedInstanceState: Bundle?) {
        mBindingView.adapterViewModel = mViewModel
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
            R.id.btn_pic1 -> mViewModel?.loadImage()
            R.id.btn_pic2 -> mViewModel?.loadRoundImage()
            R.id.btn_pic3 -> mViewModel?.loadCircleImage()
        }
    }

}