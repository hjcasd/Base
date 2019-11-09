package com.hjc.base.ui.other.drawer;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/8 15:48
 * @Description: 自定义侧边栏(推荐)
 */
@Route(path = RoutePath.URL_DRAWER_CUSTOM)
public class DrawerCustomActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drawer_custom;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarDarkFont(true)
                .statusBarView(R.id.status_view)
                .init();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnShow.setOnClickListener(this);
        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                drawerLayout.openDrawer(Gravity.START);
                break;

                default:
                    break;
        }
    }

}
