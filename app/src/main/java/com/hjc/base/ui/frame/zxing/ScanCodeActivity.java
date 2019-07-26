package com.hjc.base.ui.frame.zxing;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

@Route(path = RoutePath.URL_SCAN_CODE)
public class ScanCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.zxing_view)
    ZXingView zxingView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_code;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        zxingView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        zxingView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZXingView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        zxingView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        zxingView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zxingView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        LogUtils.e("result: " + result);
        ToastUtils.showShort(result);
        zxingView.startSpot();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {

    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }
}
