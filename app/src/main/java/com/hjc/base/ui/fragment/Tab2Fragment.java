package com.hjc.base.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.base.utils.permission.PermissionCallBack;
import com.hjc.base.utils.permission.PermissionManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:43
 * @Description: 常用框架使用
 */
public class Tab2Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_event_bus)
    Button btnEventBus;
    @BindView(R.id.btn_bugly)
    Button btnBugly;
    @BindView(R.id.btn_quick)
    Button btnQuick;
    @BindView(R.id.btn_code)
    Button btnCode;


    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab2;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        CrashReport.setUserSceneTag(mContext, 98839); // 上报后的Crash会显示该标签
    }

    @Override
    public void addListeners() {
        btnEventBus.setOnClickListener(this);
        btnBugly.setOnClickListener(this);
        btnQuick.setOnClickListener(this);
        btnCode.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_event_bus:
                SchemeUtils.jump(RoutePath.URL_EVENT_POST);
                break;

            case R.id.btn_bugly:
                ToastUtils.showShort("测试bugly结果" + 2 / 0);
                CrashReport.testJavaCrash();
                break;

            case R.id.btn_quick:
                SchemeUtils.jump(RoutePath.URL_LIST_HELPER);
                break;

            case R.id.btn_code:
                requestPermissions();
                break;
        }
    }

    private void requestPermissions() {
        PermissionManager.getInstance()
                .with(this)
                .requestPermissionInFragment(new PermissionCallBack() {
                    @Override
                    public void onGranted() {
                        SchemeUtils.jump(RoutePath.URL_QR_CODE);
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("申请相机权限失败");
                    }
                }, Permission.CAMERA);
    }
}
