package com.hjc.base.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.BarUtils;
import com.hjc.base.R;
import com.hjc.base.databinding.DialogSeatInfoBinding;
import com.hjc.base.viewmodel.CommonViewModel;
import com.hjc.library_base.dialog.BaseFragmentDialog;

/**
 * @Author: HJC
 * @Date: 2021/9/26 14:03
 * @Description: 座位信息dialog
 */
public class SeatInfoDialog extends BaseFragmentDialog<DialogSeatInfoBinding, CommonViewModel> {

    public static SeatInfoDialog newInstance(){
        return new SeatInfoDialog();
    }

    @Override
    public int getStyleRes() {
        return R.style.Base_Dialog_Right;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_seat_info;
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
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mBindingView.llRight.getLayoutParams();
        layoutParams.rightMargin = BarUtils.getStatusBarHeight();
        mBindingView.llRight.setLayoutParams(layoutParams);
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
