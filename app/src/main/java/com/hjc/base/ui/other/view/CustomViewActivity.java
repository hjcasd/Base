package com.hjc.base.ui.other.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义View
 */
@Route(path = RoutePath.URL_VIEW)
public class CustomViewActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_base)
    Button btnBase;
    @BindView(R.id.btn_canvas)
    Button btnCanvas;
    @BindView(R.id.btn_picture)
    Button btnPicture;
    @BindView(R.id.btn_path)
    Button btnPath;
    @BindView(R.id.btn_radar)
    Button btnRadar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_view;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnBase.setOnClickListener(this);
        btnCanvas.setOnClickListener(this);
        btnPicture.setOnClickListener(this);
        btnPath.setOnClickListener(this);
        btnRadar.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_base:
                SchemeUtils.jump(RoutePath.URL_VIEW_BASE);
                break;

            case R.id.btn_canvas:
                SchemeUtils.jump(RoutePath.URL_VIEW_CANVAS);
                break;

            case R.id.btn_picture:
                SchemeUtils.jump(RoutePath.URL_VIEW_PICTURE);
                break;

            case R.id.btn_path:
                SchemeUtils.jump(RoutePath.URL_VIEW_PATH);
                break;

            case R.id.btn_radar:
                SchemeUtils.jump(RoutePath.URL_VIEW_RADAR);
                break;

            default:
                break;
        }
    }

}
