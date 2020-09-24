package com.hjc.base.ui.senior.activity.view;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityViewPathBinding;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/24 14:56
 * @Description: 自定义View
 */
@Route(path = RoutePath.URL_VIEW_PATH)
public class ViewPathActivity extends BaseMvmActivity<ActivityViewPathBinding, CommonViewModel> {


    @Override
    public int getLayoutId() {
        return R.layout.activity_view_path;
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

            default:
                break;
        }
    }
}
