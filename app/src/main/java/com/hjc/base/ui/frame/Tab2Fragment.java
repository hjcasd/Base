package com.hjc.base.ui.frame;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.FragmentTab2Binding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.fragment.BaseMvmFragment;
import com.hjc.baselib.viewmodel.CommonViewModel;


/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Room
 */
public class Tab2Fragment extends BaseMvmFragment<FragmentTab2Binding, CommonViewModel> {

    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab2;
    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
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
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                RouteManager.jump(RoutePath.URL_LOAD_SIR);
                break;

            case R.id.btn2:
                RouteManager.jump(RoutePath.URL_EVENT_POST);
                break;

            case R.id.btn3:
                RouteManager.jump(RoutePath.URL_QR_CODE);
                break;

            case R.id.btn4:
                RouteManager.jumpToWeb("百度一下", "https://www.baidu.com");
                break;

            default:
                break;
        }
    }

}
