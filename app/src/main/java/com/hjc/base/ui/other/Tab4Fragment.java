package com.hjc.base.ui.other;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.FragmentTab4Binding;
import com.hjc.base.widget.dialog.UpdateDialog;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.fragment.BaseMvmFragment;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: Tab1
 */
public class Tab4Fragment extends BaseMvmFragment<FragmentTab4Binding, CommonViewModel> {

    public static Tab4Fragment newInstance() {
        return new Tab4Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab4;
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
                UpdateDialog.newInstance().showDialog(getChildFragmentManager());
                break;

            case R.id.btn2:
                RouteManager.jump(RoutePath.URL_LOGIN);
                break;

            default:
                break;
        }
    }

}
