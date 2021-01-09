package com.hjc.base.http.observer;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.exception.ApiException;
import com.hjc.base.http.exception.ExceptionUtils;
import com.hjc.base.http.exception.ServerCode;
import com.hjc.baselib.viewmodel.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
public abstract class BaseProgressObserver<T> implements Observer<BaseResponse<T>> {
    private final BaseViewModel mBaseViewModel;

    public BaseProgressObserver(BaseViewModel baseViewModel) {
        this.mBaseViewModel = baseViewModel;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mBaseViewModel.addDisposable(d);
        mBaseViewModel.showLoading();
    }

    @Override
    public void onNext(@NonNull BaseResponse<T> response) {
        if (ServerCode.CODE_SUCCESS.equals(response.getCode())) {  //请求成功
            if (response.getData() == null) {
                onSuccess((T) "");
            } else {
                onSuccess(response.getData());
            }
        } else {
            //请求成功,Code错误,抛出ApiException
            onError(new ApiException(response.getMessage(), response.getCode()));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e);
    }

    protected void onFailure(@NonNull Throwable e) {
        String errorMsg = ExceptionUtils.handleException(e);
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void onComplete() {
        mBaseViewModel.dismissLoading();
    }

    public abstract void onSuccess(T result);
}
