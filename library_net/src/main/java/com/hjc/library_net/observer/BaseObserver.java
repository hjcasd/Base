package com.hjc.library_net.observer;

import androidx.annotation.NonNull;

import com.hjc.library_base.viewmodel.BaseViewModel;
import com.hjc.library_net.bean.BaseResponse;
import com.hjc.library_net.exception.ApiException;
import com.hjc.library_net.exception.ServerCode;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 带加载框的Observer基类
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private final BaseViewModel mBaseViewModel;
    private boolean mIsShowLoading = false;

    private Disposable mDisposable;


    public BaseObserver(BaseViewModel baseViewModel) {
        this.mBaseViewModel = baseViewModel;
    }

    public BaseObserver(BaseViewModel baseViewModel, boolean isShowLoading) {
        this.mBaseViewModel = baseViewModel;
        this.mIsShowLoading = isShowLoading;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        mDisposable = d;
        mBaseViewModel.addDisposable(d);
        if (mIsShowLoading) {
            mBaseViewModel.showLoading();
        }
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (ServerCode.CODE_SUCCESS.equals(response.getErrorCode())) {
            //请求成功,Code正确
            onSuccess(response.getData());
        } else {
            //请求成功,Code错误,抛出ApiException
            onFailure(new ApiException(response.getErrorMsg(), response.getErrorCode()), response);
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        dispose();
        onFailure(e, null);
    }

    @Override
    public void onComplete() {
        dispose();
    }

    private void dispose() {
        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mIsShowLoading) {
            mBaseViewModel.dismissLoading();
        }
    }

    public abstract void onSuccess(T response);

    public abstract void onFailure(@NonNull Throwable e, BaseResponse<T> response);

}
