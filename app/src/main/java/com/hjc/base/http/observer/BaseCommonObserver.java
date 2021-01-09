package com.hjc.base.http.observer;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.http.exception.ExceptionUtils;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的Observer
 */
public abstract class BaseCommonObserver<T> extends BaseObserver<T> {

    @Override
    public void onFailure(@NonNull Throwable e) {
        String errorMsg = ExceptionUtils.handleException(e);
        ToastUtils.showShort(errorMsg);
    }

}
