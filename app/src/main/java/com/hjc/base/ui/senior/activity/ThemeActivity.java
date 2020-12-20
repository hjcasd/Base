package com.hjc.base.ui.senior.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityThemeBinding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.utils.helper.ActivityHelper;
import com.hjc.baselib.viewmodel.CommonViewModel;

@Route(path = RoutePath.URL_THEME)
public class ThemeActivity extends BaseMvmActivity<ActivityThemeBinding, CommonViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_theme;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    protected void onSingleClick(View v) {
        if (v.getId() == R.id.btn_switch) {
            int mode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

            if (mode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else if (mode == Configuration.UI_MODE_NIGHT_NO) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            ActivityHelper.finishAllActivities();
            RouteManager.jumpWithTransition(RoutePath.URL_SPLASH, R.anim.fade_in, R.anim.fade_out, this);
            finish();
        }
    }
}
