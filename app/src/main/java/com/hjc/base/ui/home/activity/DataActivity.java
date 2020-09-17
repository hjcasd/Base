package com.hjc.base.ui.home.activity;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.bean.PersonBean;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityDataBinding;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2020/5/14 15:27
 * @Description: DataBinding
 */
@Route(path = RoutePath.URL_DATA_BINDING)
public class DataActivity extends BaseMvmActivity<ActivityDataBinding, CommonViewModel> {

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
    protected int getBindingVariable() {
        return 0;
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

    @Override
    protected void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btnShow:
                loginBean.name.set("哈哈哈");
                loginBean.isShow.set(true);
                break;

            case R.id.btnChange:
                loginBean.name.set("呵呵呵");
                loginBean.isShow.set(true);
                break;

            case R.id.btnHide:
                loginBean.isShow.set(false);
                break;
        }
    }

}
