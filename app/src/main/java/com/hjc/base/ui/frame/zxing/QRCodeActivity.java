package com.hjc.base.ui.frame.zxing;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityQrCodeBinding;
import com.hjc.base.http.RxSchedulers;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;
import com.permissionx.guolindev.PermissionX;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import io.reactivex.Observable;
import io.reactivex.observers.DefaultObserver;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:28
 * @Description: Zxing的使用
 */
@Route(path = RoutePath.URL_QR_CODE)
public class QRCodeActivity extends BaseActivity<ActivityQrCodeBinding, CommonViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan:
                scanCode();
                break;

            case R.id.btn_generate:
                generate();
                break;

            case R.id.btn_generate_logo:
                generateWithLogo();
                break;

            case R.id.btn_recognition:
                recognition();
                break;

            default:
                break;
        }
    }

    /**
     * 扫描二维码
     */
    private void scanCode() {
        PermissionX.init(this)
                .permissions(Manifest.permission.CAMERA)
                .onExplainRequestReason((scope, deniedList) -> scope.showRequestReasonDialog(deniedList, "请授予以下权限，以便程序继续运行", "确定", "取消"))
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        RouteManager.jump(RoutePath.URL_SCAN_CODE);
                    } else {
                        ToastUtils.showShort("申请相机权限失败");
                    }
                });
    }

    /**
     * 生成二维码
     */
    private void generate() {
        Observable
                .just("王者荣耀")
                .map(s -> QRCodeEncoder.syncEncodeQRCode(s, BGAQRCodeUtil.dp2px(QRCodeActivity.this, 160)))
                .compose(RxSchedulers.ioToMain())
                .subscribe(new DefaultObserver<Bitmap>() {
                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        mBindingView.ivCodePic.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtils.showShort("生成二维码失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 生成带logo的二维码
     */
    private void generateWithLogo() {
        Observable
                .just("英雄联盟")
                .map(s -> {
                    Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo);
                    return QRCodeEncoder.syncEncodeQRCode(s, BGAQRCodeUtil.dp2px(QRCodeActivity.this, 160),
                            Color.parseColor("#ff0000"), logoBitmap);
                })
                .compose(RxSchedulers.ioToMain())
                .subscribe(new DefaultObserver<Bitmap>() {
                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        mBindingView.ivCodePic.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtils.showShort("生成带logo的二维码失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 识别二维码
     */
    private void recognition() {
        Drawable drawable = mBindingView.ivCodePic.getDrawable();
        if (drawable == null) {
            ToastUtils.showShort("请生成二维码");
            return;
        }
        Observable
                .just(drawable)
                .map(drawable1 -> {
                    Bitmap bitmap = ImageUtils.drawable2Bitmap(drawable1);
                    return QRCodeDecoder.syncDecodeQRCode(bitmap);
                })
                .compose(RxSchedulers.ioToMain())
                .subscribe(new DefaultObserver<String>() {
                    @Override
                    public void onNext(@NonNull String s) {
                        mBindingView.tvDesc.setText(s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtils.showShort("解析二维码失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
