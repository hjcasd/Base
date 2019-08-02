package com.hjc.base.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseFragmentActivity;
import com.hjc.base.ui.fragment.Tab1Fragment;
import com.hjc.base.ui.fragment.Tab2Fragment;
import com.hjc.base.ui.fragment.Tab3Fragment;
import com.hjc.base.ui.fragment.Tab4Fragment;
import com.hjc.base.utils.permission.PermissionCallBack;
import com.hjc.base.utils.permission.PermissionManager;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:25
 * @Description: 主页
 */
public class MainActivity extends BaseFragmentActivity {
    @BindView(R.id.rg_tab)
    RadioGroup rgTab;

    private Tab1Fragment mTab1Fragment;
    private Tab2Fragment mTab2Fragment;
    private Tab3Fragment mTab3Fragment;
    private Tab4Fragment mTab4Fragment;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        requestPermission();

        mTab1Fragment = Tab1Fragment.newInstance();
        mTab2Fragment = Tab2Fragment.newInstance();
        mTab3Fragment = Tab3Fragment.newInstance();
        mTab4Fragment = Tab4Fragment.newInstance();

        showFragment(mTab1Fragment);
    }

    private void requestPermission(){
        new PermissionManager(this)
                .requestPermissionInActivity(new PermissionCallBack() {
                    @Override
                    public void onGranted() {
                        ToastUtils.showShort("申请存储权限成功");
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("申请存储权限失败");
                    }
                }, Permission.Group.STORAGE);
    }

    @Override
    public void addListeners() {
        rgTab.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.rb_tab1:
                    showFragment(mTab1Fragment);
                    break;

                case R.id.rb_tab2:
                    showFragment(mTab2Fragment);
                    break;

                case R.id.rb_tab3:
                    showFragment(mTab3Fragment);
                    break;

                case R.id.rb_tab4:
                    showFragment(mTab4Fragment);
                    break;

                default:
                    break;
            }
        });
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fl_content;
    }

    @Override
    public void onSingleClick(View v) {

    }
}
