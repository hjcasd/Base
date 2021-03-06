package com.hjc.base.ui.home.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.bean.PersonBean;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityDataBinding;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: DataBinding
 */
@Route(path = RoutePath.URL_DATA_BINDING)
public class DataActivity extends BaseActivity<ActivityDataBinding, CommonViewModel> {

    private PersonBean loginBean = new PersonBean();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_data;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        mBindingView.setPerson(loginBean);
    }

    @Override
    protected void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                loginBean.name.set("哈哈哈");
                loginBean.isShow.set(true);
                break;

            case R.id.btn_change:
                loginBean.name.set("呵呵呵");
                loginBean.isShow.set(true);
                break;

            case R.id.btn_hide:
                loginBean.isShow.set(false);
                break;
        }
    }

}
