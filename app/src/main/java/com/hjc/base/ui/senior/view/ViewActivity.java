package com.hjc.base.ui.senior.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityViewBinding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义View
 */
@Route(path = RoutePath.URL_VIEW)
public class ViewActivity extends BaseActivity<ActivityViewBinding, CommonViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_view;
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
            case R.id.btn_base:
                RouteManager.jump(RoutePath.URL_VIEW_BASE);
                break;

            case R.id.btn_canvas:
                RouteManager.jump(RoutePath.URL_VIEW_CANVAS);
                break;

            case R.id.btn_picture:
                RouteManager.jump(RoutePath.URL_VIEW_PICTURE);
                break;

            case R.id.btn_path:
                RouteManager.jump(RoutePath.URL_VIEW_PATH);
                break;

            case R.id.btn_radar:
                RouteManager.jump(RoutePath.URL_VIEW_RADAR);
                break;

            default:
                break;
        }
    }

}
