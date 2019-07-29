package com.hjc.base.http.observer;

import android.support.v4.app.FragmentManager;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.exception.ApiException;
import com.hjc.base.http.exception.ExceptionUtils;
import com.hjc.base.http.exception.ServerCode;
import com.hjc.base.widget.dialog.LoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
public abstract class BaseProgressObserver<T> implements Observer<BaseResponse<T>> {
    private LoadingDialog mLoadingDialog;
    private FragmentManager mFragmentManager;
    private boolean isShowLoading = true;

    public BaseProgressObserver(FragmentManager fragmentManager) {
        this.mFragmentManager = fragmentManager;
        mLoadingDialog = LoadingDialog.newInstance();
    }

    public BaseProgressObserver(FragmentManager fragmentManager, boolean isShow) {
        this.mFragmentManager = fragmentManager;
        this.isShowLoading = isShow;
        mLoadingDialog = LoadingDialog.newInstance();
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (isShowLoading) {
            showLoading();
        }
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (response == null) {
            onError(new ApiException("服务器返回异常", "-1"));
            return;
        }

        if (ServerCode.CODE_SUCCESS.equals(response.getCode())) {  //请求成功
            if (response.getData() == null) {
                if (isShowLoading) {
                    hideLoading();
                }
                onSuccess((T) "");
            } else {
                if (isShowLoading) {
                    hideLoading();
                }
                onSuccess(response.getData());
            }
        } else {
            //请求成功,Code错误,抛出ApiException
            onError(new ApiException(response.getMessage(), response.getCode()));
        }
    }

    @Override
    public void onError(Throwable e) {
        if (isShowLoading) {
            hideLoading();
            onFailure(ExceptionUtils.handleException(e));
        }
    }

    protected void onFailure(String errorMsg){
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void onComplete() {
        if (isShowLoading) {
            hideLoading();
        }
    }

    private void showLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.showDialog(mFragmentManager);
        }
    }

    private void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public abstract void onSuccess(T result);
}
