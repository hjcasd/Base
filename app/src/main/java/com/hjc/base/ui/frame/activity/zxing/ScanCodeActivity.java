package com.hjc.base.ui.frame.activity.zxing;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityScanCodeBinding;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 扫描二维码
 */
@Route(path = RoutePath.URL_SCAN_CODE)
public class ScanCodeActivity extends BaseMvmActivity<ActivityScanCodeBinding, CommonViewModel> implements QRCodeView.Delegate {

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_code;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mBindingView.zxingView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBindingView.zxingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
        mBindingView.zxingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mBindingView.zxingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mBindingView.zxingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.e("result: " + result);
        ToastUtils.showShort(result);
        mBindingView.zxingView.startSpot();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
