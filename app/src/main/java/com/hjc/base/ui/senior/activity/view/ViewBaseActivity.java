package com.hjc.base.ui.senior.activity.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityViewBaseBinding;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 绘制基本图形
 */
@Route(path = RoutePath.URL_VIEW_BASE)
public class ViewBaseActivity extends BaseMvmActivity<ActivityViewBaseBinding, CommonViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_view_base;
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
            case R.id.btn4:
                mBindingView.customView.draw(4);
                break;

            case R.id.btn5:
                mBindingView.customView.draw(5);
                break;

            case R.id.btn6:
                mBindingView.customView.draw(6);
                break;

            case R.id.btn7:
                mBindingView.customView.draw(7);
                break;

            case R.id.btn8:
                mBindingView.customView.draw(8);
                break;

            case R.id.btn9:
                mBindingView.customView.draw(9);
                break;

            default:
                break;
        }
    }
}
