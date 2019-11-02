package com.hjc.base.ui.other.drawer;

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
 * @Date: 2019/10/24 15:24
 * @Description: 侧边栏
 */
@Route(path = RoutePath.URL_DRAWER)
public class DrawerActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_drawer_navigation)
    Button btnDrawerNavigation;
    @BindView(R.id.btn_drawer_custom)
    Button btnDrawerCustom;

    @Override
    public int getLayoutId() {
        return R.layout.activity_drawer;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnDrawerNavigation.setOnClickListener(this);
        btnDrawerCustom.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_drawer_navigation:
                SchemeUtils.jump(RoutePath.URL_DRAWER_NAVIGATION);
                break;

            case R.id.btn_drawer_custom:
                SchemeUtils.jump(RoutePath.URL_DRAWER_CUSTOM);
                break;

            default:
                break;
        }

    }
}
