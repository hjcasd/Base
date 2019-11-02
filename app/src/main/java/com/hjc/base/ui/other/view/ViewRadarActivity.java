package com.hjc.base.ui.other.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.other.view.example.RadarView;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义雷达图
 */
@Route(path = RoutePath.URL_VIEW_RADAR)
public class ViewRadarActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.radar_view)
    RadarView radarView;


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_radar;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }
}
