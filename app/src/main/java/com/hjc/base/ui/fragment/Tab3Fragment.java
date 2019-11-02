package com.hjc.base.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.utils.PhotoUtils;
import com.hjc.baselib.fragment.BaseImmersionFragment;
import com.hjc.baselib.utils.permission.PermissionCallBack;
import com.hjc.baselib.utils.permission.PermissionManager;
import com.yanzhenjie.permission.runtime.Permission;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:44
 * @Description: 多媒体框架使用及常用功能实现
 */
public class Tab3Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_camera)
    Button btnCamera;


    public static Tab3Fragment newInstance() {
        return new Tab3Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab3;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnCamera.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_camera:
                openCamera();
                break;

            default:
                break;
        }
    }

    /**
     * 打开相机
     */
    private void openCamera() {
        new PermissionManager(this)
                .requestPermissionInFragment(new PermissionCallBack() {
                    @Override
                    public void onGranted() {
                        PhotoUtils.openCamera(getActivity());
                    }

                    @Override
                    public void onDenied() {
                        ToastUtils.showShort("申请相机权限失败");
                    }
                }, Permission.Group.CAMERA);
    }

}
