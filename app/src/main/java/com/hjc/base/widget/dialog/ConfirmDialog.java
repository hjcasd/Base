package com.hjc.base.widget.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hjc.base.R;
import com.hjc.base.base.dialog.BaseDialog;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:28
 * @Description: 确认取消dialog
 */
public class ConfirmDialog extends BaseDialog {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    public static ConfirmDialog newInstance() {
        return new ConfirmDialog();
    }

    @Override
    public int getStyleRes() {
        return R.style.Dialog_Base;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_confirm;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        tvCancel.setOnClickListener(this);
        tvConfirm.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;

            case R.id.tv_confirm:
                dismiss();
                break;

            default:
                break;
        }
    }
}
