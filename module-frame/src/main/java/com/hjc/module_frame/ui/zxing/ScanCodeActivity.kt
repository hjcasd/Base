package com.hjc.module_frame.ui.zxing

import android.os.Bundle
import android.view.View
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteFramePath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameActivityScanCodeBinding

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 扫描二维码
 */
@Route(path = RouteFramePath.URL_SCAN_CODE)
class ScanCodeActivity : BaseActivity<FrameActivityScanCodeBinding, CommonViewModel>(),
    QRCodeView.Delegate {

    override fun getLayoutId(): Int {
        return R.layout.frame_activity_scan_code
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
        mBindingView.zxingView.setDelegate(this)
    }

    override fun onStart() {
        super.onStart()
        mBindingView.zxingView.startCamera() // 打开后置摄像头开始预览，但是并未开始识别
        mBindingView.zxingView.startSpotAndShowRect() // 显示扫描框，并开始识别
    }

    override fun onStop() {
        mBindingView.zxingView.stopCamera() // 关闭摄像头预览，并且隐藏扫描框
        super.onStop()
    }

    override fun onDestroy() {
        mBindingView.zxingView.onDestroy() // 销毁二维码扫描控件
        super.onDestroy()
    }

    override fun addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {

    }

    override fun onScanQRCodeSuccess(result: String) {
        LogUtils.e("result: $result")
        ToastUtils.showShort(result)
        mBindingView.zxingView.startSpot()
    }

    override fun onCameraAmbientBrightnessChanged(isDark: Boolean) {

    }

    override fun onScanQRCodeOpenCameraError() {

    }

}