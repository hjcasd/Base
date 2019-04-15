package com.hjc.base.http.observer;

import android.support.v4.app.FragmentManager;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.utils.dialog.LoadingDialog;

import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
public abstract class ProgressObserver<T> extends BaseObserver<T> {
    private LoadingDialog loadingDialog;

    private FragmentManager fragmentManager;

    public ProgressObserver() {
    }

    public ProgressObserver(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        loadingDialog = LoadingDialog.newInstance();
    }

    @Override
    public void onSubscribe(Disposable d) {
        super.onSubscribe(d);
        showLoading();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        hideLoading();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        hideLoading();
    }

    @Override
    public void onFailure(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    private void showLoading() {
        if (loadingDialog != null) {
            loadingDialog.showDialog(fragmentManager);
        }
    }

    private void hideLoading() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
