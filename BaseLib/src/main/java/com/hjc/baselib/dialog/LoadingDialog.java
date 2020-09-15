package com.hjc.baselib.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.hjc.baselib.R;
import com.hjc.baselib.databinding.DialogLoadingBinding;
import com.hjc.baselib.viewmodel.BaseViewModel;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:28
 * @Description: 加载框
 */
public class LoadingDialog extends BaseFragmentDialog<DialogLoadingBinding, BaseViewModel> {

    public static LoadingDialog newInstance() {
        return new LoadingDialog();
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
    protected BaseViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        //去掉遮盖层
//        Window window = getDialog().getWindow();
//        if (window != null) {
//            window.setDimAmount(0f);
//        }
        setCancelable(true);
    }

//    @SuppressLint("CheckResult")
//    public void dismissDialog(){
//        Observable.timer(500, TimeUnit.MILLISECONDS)
//                .compose(RxSchedulers.ioToMain())
//                .subscribe(aLong -> dismissAllowingStateLoss());
//    }


    @Override
    public void addListeners() {

    }

    @Override
    public void onSingleClick(View v) {

    }
}
