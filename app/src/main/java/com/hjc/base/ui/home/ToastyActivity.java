package com.hjc.base.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

@Route(path = RoutePath.URL_TOASTY)
public class ToastyActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_error)
    Button btnError;
    @BindView(R.id.btn_success)
    Button btnSuccess;
    @BindView(R.id.btn_info)
    Button btnInfo;
    @BindView(R.id.btn_warning)
    Button btnWarning;
    @BindView(R.id.btn_normal)
    Button btnNormal;
    @BindView(R.id.btn_custom)
    Button btnCustom;

    @Override
    public int getLayoutId() {
        return R.layout.activity_toasty;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnError.setOnClickListener(this);
        btnSuccess.setOnClickListener(this);
        btnInfo.setOnClickListener(this);
        btnWarning.setOnClickListener(this);
        btnNormal.setOnClickListener(this);
        btnCustom.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()){
            case R.id.btn_error:
                Toasty.error(this, "我是error提示!").show();
                break;

            case R.id.btn_success:
                Toasty.success(this, "我是success提示!").show();
                break;

            case R.id.btn_info:
                Toasty.info(this, "我是info提示!").show();
                break;

            case R.id.btn_warning:
                Toasty.warning(this, "我是warning提示!").show();
                break;

            case R.id.btn_normal:
                Toasty.normal(this, "我是normal提示!").show();
                break;

            case R.id.btn_custom:
                Toasty.custom(this, "自定义Toast", R.mipmap.ic_launcher, R.color.colorPrimary, Toast.LENGTH_LONG, true, true).show();
                break;
        }
    }
}
