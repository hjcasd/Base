package com.hjc.base.ui.other.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.other.view.example.MyCustom1View;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 绘制基本图形
 */
@Route(path = RoutePath.URL_VIEW_BASE)
public class ViewBaseActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.custom_view)
    MyCustom1View customView;
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_base;
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
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);

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
            case R.id.btn4:
                customView.draw(4);
                break;

            case R.id.btn5:
                customView.draw(5);
                break;

            case R.id.btn6:
                customView.draw(6);
                break;

            case R.id.btn7:
                customView.draw(7);
                break;

            case R.id.btn8:
                customView.draw(8);
                break;

            case R.id.btn9:
                customView.draw(9);
                break;

            default:
                break;
        }
    }
}
