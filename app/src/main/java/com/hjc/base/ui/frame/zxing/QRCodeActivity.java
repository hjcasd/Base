package com.hjc.base.ui.frame.zxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

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
                SchemeUtils.jump(RoutePath.URL_SCAN_CODE);
                break;

            case R.id.btn_generate:
                Bitmap bitmap1 = QRCodeEncoder.syncEncodeQRCode("哈哈哈", BGAQRCodeUtil.dp2px(QRCodeActivity.this, 160));
                if (bitmap1 != null) {
                    ivCodePic.setImageBitmap(bitmap1);
                } else {
                    ToastUtils.showShort("生成中文二维码失败");
                }
                break;

            case R.id.btn_generate_logo:
                Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                Bitmap bitmap2 = QRCodeEncoder.syncEncodeQRCode("呵呵呵", BGAQRCodeUtil.dp2px(QRCodeActivity.this, 160),
                        Color.parseColor("#ff0000"), logoBitmap);
                if (bitmap2 != null) {
                    ivCodePic.setImageBitmap(bitmap2);
                } else {
                    ToastUtils.showShort("生成带logo的二维码失败");
                }
                break;

            case R.id.btn_recognition:
                ToastUtils.showShort("11111111");
                break;
        }
    }
}
