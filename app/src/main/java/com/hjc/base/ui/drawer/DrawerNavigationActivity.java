package com.hjc.base.ui.drawer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.widget.bar.OnViewLeftClickListener;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/8 15:49
 * @Description: DrawerLayout+navigation(适合侧边栏为简单布局)
 */
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
        ImmersionBar.with(this).statusBarView(R.id.status_view).init();
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
        titleBar.setOnViewLeftClickListener(new OnViewLeftClickListener() {
            @Override
            public void leftClick(View view) {
                finish();
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
                }
                return false;
            }
        });
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()){
            case R.id.btn_show:
                drawerLayout.openDrawer(Gravity.START);
                break;
        }
    }

}
