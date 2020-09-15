package com.hjc.baselib.http.observer;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.baselib.http.bean.BaseResponse;
import com.hjc.baselib.http.exception.ApiException;
import com.hjc.baselib.http.exception.ExceptionUtils;
import com.hjc.baselib.http.exception.ServerCode;
import com.hjc.baselib.viewmodel.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
public abstract class BaseProgressObserver<T> implements Observer<BaseResponse<T>> {
    private BaseViewModel mBaseViewModel;

    public BaseProgressObserver(BaseViewModel baseViewModel) {
        this.mBaseViewModel = baseViewModel;
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseViewModel != null){
            mBaseViewModel.addDisposable(d);
            mBaseViewModel.startLoading();
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
    public void onError(Throwable e) {
        if (mBaseViewModel != null){
            mBaseViewModel.dismissLoading();
        }
        onFailure(ExceptionUtils.handleException(e));
    }

    protected void onFailure(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void onComplete() {
        if (mBaseViewModel != null){
            mBaseViewModel.dismissLoading();
        }
    }


    public abstract void onSuccess(T result);
}
