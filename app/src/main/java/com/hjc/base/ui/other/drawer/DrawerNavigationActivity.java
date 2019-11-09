package com.hjc.base.ui.other.drawer;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/8 15:49
 * @Description: DrawerLayout+navigation(适合侧边栏为简单布局)
 */
@Route(path = RoutePath.URL_DRAWER_NAVIGATION)
public class DrawerNavigationActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_show)
    Button btnShow;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drawer_navigation;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
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

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.nav_item1:
                    ToastUtils.showShort("item1");
                    break;

                case R.id.nav_item3:
                    ToastUtils.showShort("item3");
                    break;

                case R.id.nav_item5:
                    ToastUtils.showShort("item5");
                    break;

                default:
                    break;
            }
            return false;
        });
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
