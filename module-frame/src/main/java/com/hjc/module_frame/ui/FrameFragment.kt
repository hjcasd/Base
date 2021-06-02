package com.hjc.module_frame.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_common.router.RouteManager
import com.hjc.library_common.router.RoutePath
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameFragmentBinding
import com.hjc.module_frame.utils.LocationUtils

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab2
 */
@Route(path = RoutePath.Frame.URL_FRAME_FRAGMENT)
class FrameFragment : BaseFragment<FrameFragmentBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.frame_fragment
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> RouteManager.jump(RoutePath.Frame.URL_LOAD_SIR)
            R.id.btn2 -> RouteManager.jump(RoutePath.Frame.URL_EVENT_POST)
            R.id.btn3 -> RouteManager.jump(RoutePath.Frame.URL_QR_CODE)
            R.id.btn4 -> {
                if (!LocationUtils.isGpsEnabled()) {
                    showOpenGPSDialog()
                }else{
                    RouteManager.jumpToWeb("万达电影", "https://m.wandacinemas.com")
                }
            }
            else -> {
            }
        }
    }

    private fun showOpenGPSDialog() {
        AlertDialog.Builder(mContext)
            .setTitle("定位服务未开启")
            .setMessage("需要获取您的位置信息才能继续提供服务，请在系统设置中开启定位服务")
            .setNegativeButton(
                "取消"
            ) { dialog: DialogInterface, which: Int -> dialog.dismiss() }
            .setPositiveButton("去设置") { dialog: DialogInterface?, which: Int ->
                dialog?.dismiss()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(intent, 1000)
            }
            .setCancelable(false)
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000){
            RouteManager.jumpToWeb("万达电影", "https://m.wandacinemas.com")
        }
    }
}