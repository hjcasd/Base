package com.hjc.base.utils.dialog;

import android.os.Bundle;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.hjc.base.R;
import com.hjc.base.base.dialog.BaseDialog;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:28
 * @Description: 加载框
 */
public class LoadingDialog extends BaseDialog {
    @BindView(R.id.spin_kit_view)
    SpinKitView spinKitView;

    public static LoadingDialog newInstance() {
        LoadingDialog loadingDialog = new LoadingDialog();
        return loadingDialog;
    }

    @Override
    public int getStyleRes() {
        return R.style.Dialog_Base;
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_loading;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        //去掉遮盖层
        getDialog().getWindow().setDimAmount(0f);
        setCancelable(false);
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void onSingleClick(View v) {

    }
}
