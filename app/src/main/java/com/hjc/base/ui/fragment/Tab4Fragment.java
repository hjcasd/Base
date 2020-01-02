package com.hjc.base.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.ui.other.update.UpdateDialog;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.baselib.fragment.BaseImmersionFragment;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:45
 * @Description: 其他框架使用及常用效果实现
 */
public class Tab4Fragment extends BaseImmersionFragment {
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_drawer)
    Button btnDrawer;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.btn_touch)
    Button btnTouch;
    @BindView(R.id.btn_view)
    Button btnView;
    @BindView(R.id.btn_view_pager)
    Button btnViewPager;


    public static Tab4Fragment newInstance() {
        return new Tab4Fragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tab4;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        btnDialog.setOnClickListener(this);
        btnDrawer.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnTouch.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewPager.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_dialog:
                SchemeUtils.jump(RoutePath.URL_DIALOG);
                break;

            case R.id.btn_drawer:
                SchemeUtils.jump(RoutePath.URL_DRAWER);
                break;

            case R.id.btn_update:
                showUpdateDialog();
                break;

            case R.id.btn_touch:
                SchemeUtils.jump(RoutePath.URL_TOUCH);
                break;

            case R.id.btn_view:
                SchemeUtils.jump(RoutePath.URL_VIEW);
                break;

            case R.id.btn_view_pager:
                SchemeUtils.jump(RoutePath.URL_VIEW_PAGER);
                break;

            default:
                break;
        }
    }

    /**
     * 显示更新dialog
     */
    private void showUpdateDialog() {
        UpdateDialog.newInstance()
                .setAnimStyle(R.style.ActionSheetDialogAnimation)
                .showDialog(getChildFragmentManager());
    }
}
