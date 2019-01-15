package com.hjc.base.ui;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseFragmentActivity;
import com.hjc.base.ui.fragment.Tab1Fragment;
import com.hjc.base.ui.fragment.Tab2Fragment;
import com.hjc.base.ui.fragment.Tab3Fragment;
import com.hjc.base.ui.fragment.Tab4Fragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;

public class MainActivity extends BaseFragmentActivity {

    @BindView(R.id.rg_tab)
    RadioGroup rg_tab;

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

    /**
     * 申请权限
     */
    private void requestPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);

        rxPermissions.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA)
                .subscribe(permission -> {
                    if (permission.granted) {
                        ToastUtils.showShort("申请存储,录音,相机权限成功");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        ToastUtils.showShort("该应用需要存储,录音,相机权限,否则可能会导致应用异常");
                    } else {
                        ToastUtils.showShort("申请存储,录音,相机权限失败");
                    }
                });
    }

    @Override
    public void addListeners() {
        rg_tab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
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
