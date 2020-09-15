package com.hjc.base.http.observer;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.baselib.http.exception.ApiException;
import com.hjc.baselib.http.exception.ExceptionUtils;
import com.hjc.baselib.viewmodel.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
public abstract class BasePageObserver<T> implements Observer<T> {
    private BaseViewModel mBaseViewModel;
    private boolean mIsFirst;

    public BasePageObserver(BaseViewModel baseViewModel, boolean isFirst) {
        this.mBaseViewModel = baseViewModel;
        this.mIsFirst = isFirst;
    }


    @Override
    public void onSubscribe(Disposable d) {
        if (mBaseViewModel != null) {
            mBaseViewModel.addDisposable(d);
            if (mIsFirst){
                mBaseViewModel.showLoading();
            }
        }
    }

    @Override
    public void onNext(T response) {
        if (response == null) {
            onError(new ApiException("服务器返回异常", "-1"));
            return;
        }
        if (mBaseViewModel != null) {
            mBaseViewModel.showContent();
        }
        onSuccess(response);
    }

    @Override
    public void onError(Throwable e) {
        if (mBaseViewModel != null) {
            mBaseViewModel.showError();
        }
        onFailure(ExceptionUtils.handleException(e));
    }

    public void onFailure(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T result);
}
