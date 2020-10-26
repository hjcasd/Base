package com.hjc.base.ui.senior.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityMotionBinding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

@Route(path = RoutePath.URL_MOTION)
public class MotionActivity extends BaseMvmActivity<ActivityMotionBinding, CommonViewModel> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_motion;
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
        switch (v.getId()) {
            case R.id.btn1:
                RouteManager.jump(RoutePath.URL_TOUCH);
                break;

            case R.id.btn2:
                RouteManager.jump(RoutePath.URL_VIEW);
                break;

            default:
                break;
        }
    }
}
