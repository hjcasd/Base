package com.hjc.base.ui.frame.zxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ImageUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.base.utils.permission.PermissionCallBack;
import com.hjc.base.utils.permission.PermissionManager;
import com.hjc.base.widget.bar.TitleBar;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;
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
public class QRCodeActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_scan)
    Button btnScan;
    @BindView(R.id.btn_generate)
    Button btnGenerate;
    @BindView(R.id.btn_recognition)
    Button btnRecognition;
    @BindView(R.id.iv_code_pic)
    ImageView ivCodePic;
    @BindView(R.id.btn_generate_logo)
    Button btnGenerateLogo;
    @BindView(R.id.tv_desc)
    TextView tvDesc;

    @Override
    public int getLayoutId() {
        return R.layout.activity_qr_code;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void addListeners() {
        btnScan.setOnClickListener(this);
        btnGenerate.setOnClickListener(this);
        btnGenerateLogo.setOnClickListener(this);
        btnRecognition.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

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
        new PermissionManager(this)
                .requestPermissionInActivity(new PermissionCallBack() {
                    @Override
                    public void onGranted() {
                        SchemeUtils.jump(RoutePath.URL_SCAN_CODE);
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("申请相机权限失败");
                    }
                }, Permission.CAMERA);
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
                    public void onNext(Bitmap bitmap) {
                        if (bitmap != null) {
                            ivCodePic.setImageBitmap(bitmap);
                        } else {
                            ToastUtils.showShort("生成二维码失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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
                    public void onNext(Bitmap bitmap) {
                        if (bitmap != null) {
                            ivCodePic.setImageBitmap(bitmap);
                        } else {
                            ToastUtils.showShort("生成带logo的二维码失败");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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
        Drawable drawable = ivCodePic.getDrawable();
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
                    public void onNext(String s) {
                        if (StringUtils.isEmpty(s)) {
                            ToastUtils.showShort("解析二维码失败");
                        } else {
                            tvDesc.setText(s);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
