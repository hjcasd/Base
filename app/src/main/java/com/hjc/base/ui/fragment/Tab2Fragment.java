package com.hjc.base.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.event.Event;
import com.hjc.base.base.event.EventManager;
import com.hjc.base.base.fragment.BaseImmersionFragment;
import com.hjc.base.constant.EventCode;
import com.hjc.base.ui.update.utils.UpdateHelper;
import com.hjc.base.utils.PhotoUtils;
import com.tencent.bugly.crashreport.CrashReport;

import butterknife.BindView;


public class Tab2Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_event)
    Button btnEvent;
    @BindView(R.id.btn_bugly)
    Button btnBugly;
    @BindView(R.id.btn_camera)
    Button btnCamera;

    public static Tab2Fragment newInstance() {
        Tab2Fragment fragment = new Tab2Fragment();
        return fragment;
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
        btnUpdate.setOnClickListener(this);
        btnEvent.setOnClickListener(this);
        btnBugly.setOnClickListener(this);
        btnCamera.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_update:
                UpdateHelper.check(this);
                break;

            case R.id.btn_event:
                EventManager.sendStickyEvent(new Event<>(EventCode.A, "我是event消息"));
                break;

            case R.id.btn_bugly:
                ToastUtils.showShort("测试bugly结果" + 2 / 0);
                CrashReport.testJavaCrash();
                break;

            case R.id.btn_camera:
                PhotoUtils.openCamera((Activity) mContext);
//                PhotoUtils.openAlbum((Activity) mContext);
                break;
        }
    }
}
