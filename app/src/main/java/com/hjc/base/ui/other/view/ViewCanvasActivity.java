package com.hjc.base.ui.other.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.other.view.example.MyCustom2View;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 画布操作
 */
@Route(path = RoutePath.URL_VIEW_CANVAS)
public class ViewCanvasActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.custom_view)
    MyCustom2View customView;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_canvas;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                customView.draw(1);
                break;

            case R.id.btn2:
                customView.draw(2);
                break;

            case R.id.btn3:
                customView.draw(3);
                break;

            default:
                break;
        }
    }
}
