package com.hjc.base.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.databinding.ActivityMainBinding;
import com.hjc.base.router.RoutePath;
import com.hjc.base.ui.fragment.Tab1Fragment;
import com.hjc.base.ui.fragment.Tab2Fragment;
import com.hjc.base.ui.fragment.Tab3Fragment;
import com.hjc.base.ui.fragment.Tab4Fragment;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.activity.BaseFragmentActivity;
import com.permissionx.guolindev.PermissionX;

/**
 * @Author: HJC
 * @Date: 2021/9/22 20:41
 * @Description: 主界面
 */
@Route(path = RoutePath.URL_MAIN)
public class MainActivity extends BaseFragmentActivity<ActivityMainBinding, CommonViewModel> {

    private Tab1Fragment mTab1Fragment;
    private Tab2Fragment mTab2Fragment;
    private Tab3Fragment mTab3Fragment;
    private Tab4Fragment mTab4Fragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public CommonViewModel createViewModel() {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mTab1Fragment = Tab1Fragment.newInstance();
        mTab2Fragment = Tab2Fragment.newInstance();
        mTab3Fragment = Tab3Fragment.newInstance();
        mTab4Fragment = Tab4Fragment.newInstance();

        showFragment(mTab1Fragment);

        requestPermission();
    }

    /**
     * 申请权限
     */
    private void requestPermission() {
        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        PermissionX.init(this)
                .permissions(permissions)
                .onExplainRequestReason((scope, deniedList) -> scope.showRequestReasonDialog(deniedList, "请授予以下权限，以便程序继续运行", "确定", "取消"))
                .request((allGranted, grantedList, deniedList) -> {
                    if (allGranted) {
                        ToastUtils.showShort("申请权限成功");
                    } else {
                        ToastUtils.showShort("权限申请失败: " + deniedList);
                    }
                });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void addListeners() {
        mBindingView.bottomView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_tab1:
                    showFragment(mTab1Fragment);
                    break;

                case R.id.item_tab2:
                    showFragment(mTab2Fragment);
                    break;

                case R.id.item_tab3:
                    showFragment(mTab3Fragment);
                    break;

                case R.id.item_tab4:
                    showFragment(mTab4Fragment);
                    break;
            }
            return true;
        });
    }

    @Override
    public int getFragmentContentId() {
        return R.id.fl_content;
    }

    @Override
    public void onSingleClick(View v) {

    }

}
