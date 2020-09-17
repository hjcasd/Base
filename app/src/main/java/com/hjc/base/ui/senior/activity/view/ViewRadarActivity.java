package com.hjc.base.ui.senior.activity.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityViewRadarBinding;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义雷达图
 */
@Route(path = RoutePath.URL_VIEW_RADAR)
public class ViewRadarActivity extends BaseMvmActivity<ActivityViewRadarBinding, CommonViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_radar;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }
}
