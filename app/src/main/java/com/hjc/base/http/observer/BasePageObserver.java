package com.hjc.base.http.observer;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.http.exception.ExceptionUtils;
import com.hjc.baselib.viewmodel.BaseViewModel;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带进度的Observer
 */
public abstract class BasePageObserver<T> implements Observer<T> {
    private final BaseViewModel mBaseViewModel;
    private final boolean mIsShowProgress;

    public BasePageObserver(BaseViewModel baseViewModel, boolean isShowProgress) {
        this.mBaseViewModel = baseViewModel;
        this.mIsShowProgress = isShowProgress;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mBaseViewModel.addDisposable(d);
        if (mIsShowProgress) {
            mBaseViewModel.showProgress();
        }
    }

    @Override
    public void onNext(@NonNull T response) {
        mBaseViewModel.showContent();
        onSuccess(response);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        mBaseViewModel.showError();
        onFailure(e);
    }

    protected void onFailure(@NonNull Throwable e) {
        String errorMsg = ExceptionUtils.handleException(e);
        ToastUtils.showShort(errorMsg);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T result);
}
