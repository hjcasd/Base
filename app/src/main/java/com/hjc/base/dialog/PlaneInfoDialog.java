package com.hjc.base.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.BarUtils;
import com.hjc.base.R;
import com.hjc.base.databinding.DialogPlaneInfoBinding;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.dialog.BaseFragmentDialog;

/**
 * @Author: HJC
 * @Date: 2021/9/26 14:03
 * @Description: 飞机信息dialog
 */
public class PlaneInfoDialog extends BaseFragmentDialog<DialogPlaneInfoBinding, CommonViewModel> {

    public static PlaneInfoDialog newInstance() {
        return new PlaneInfoDialog();
    }

    @Override
    public int getStyleRes() {
        return R.style.Base_Dialog_Left;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_plane_info;
    }

    @Override
    public CommonViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected int getHeight() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

    @Override
    public void initView() {
        super.initView();
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBindingView.llLeft.getLayoutParams();
        layoutParams.leftMargin = BarUtils.getStatusBarHeight();
        mBindingView.llLeft.setLayoutParams(layoutParams);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setDimAmount(0f);
        }
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void onSingleClick(View v) {

    }

}
