package com.hjc.base.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityMainBinding;
import com.hjc.base.ui.fragment.Tab1Fragment;
import com.hjc.base.ui.fragment.Tab2Fragment;
import com.hjc.base.ui.fragment.Tab3Fragment;
import com.hjc.base.ui.fragment.Tab4Fragment;
import com.hjc.baselib.activity.BaseMvmFragmentActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

@Route(path = RoutePath.URL_MAIN)
public class MainActivity extends BaseMvmFragmentActivity<ActivityMainBinding, CommonViewModel> {

    private Tab1Fragment mTab1Fragment;
    private Tab2Fragment mTab2Fragment;
    private Tab3Fragment mTab3Fragment;
    private Tab4Fragment mTab4Fragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
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
        mTab1Fragment = Tab1Fragment.newInstance();
        mTab2Fragment = Tab2Fragment.newInstance();
        mTab3Fragment = Tab3Fragment.newInstance();
        mTab4Fragment = Tab4Fragment.newInstance();

        showFragment(mTab1Fragment);
    }

    @Override
    protected void addListeners() {
        mBindingView.rgTab.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rbTab1:
                    showFragment(mTab1Fragment);
                    break;

                case R.id.rbTab2:
                    showFragment(mTab2Fragment);
                    break;

                case R.id.rbTab3:
                    showFragment(mTab3Fragment);
                    break;

                case R.id.rbTab4:
                    showFragment(mTab4Fragment);
                    break;

                default:
                    break;
            }
        });
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.flContent;
    }

    @Override
    protected void onSingleClick(View v) {

    }
}
