package com.hjc.base.http.observer;


import androidx.annotation.NonNull;

import com.hjc.base.http.bean.BaseResponse;
import com.hjc.base.http.exception.ApiException;
import com.hjc.base.http.exception.ServerCode;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 数据返回统一处理Observer基类
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    @Override
    public void onNext(BaseResponse<T> response) {
        if (ServerCode.CODE_SUCCESS.equals(response.getCode())) {  //请求成功
            onSuccess(response.getData());
        } else { //请求成功,Code错误,抛出ApiException
            onError(new ApiException(response.getMessage(), response.getCode()));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(@NonNull Throwable e);

}
