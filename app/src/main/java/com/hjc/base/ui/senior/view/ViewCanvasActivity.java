package com.hjc.base.ui.senior.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityViewCanvasBinding;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 画布操作
 */
@Route(path = RoutePath.URL_VIEW_CANVAS)
public class ViewCanvasActivity extends BaseActivity<ActivityViewCanvasBinding, CommonViewModel> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_canvas;
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
            case R.id.btn1:
                mBindingView.customView.draw(1);
                break;

            case R.id.btn2:
                mBindingView.customView.draw(2);
                break;

            case R.id.btn3:
                mBindingView.customView.draw(3);
                break;

            default:
                break;
        }
    }
}
