package com.hjc.library_net.observer;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.library_base.viewmodel.BaseViewModel;
import com.hjc.library_net.bean.BaseResponse;
import com.hjc.library_net.exception.ExceptionUtils;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的带加载框的Observer
 */
public class CommonObserver<T> extends BaseObserver<T> {

    public CommonObserver(BaseViewModel baseViewModel) {
        super(baseViewModel);
    }

    public CommonObserver(BaseViewModel baseViewModel, boolean isShowLoading) {
        super(baseViewModel, isShowLoading);
    }

    @Override
    public void onSuccess(T response) {

    }

    @Override
    public void onFailure(@NonNull Throwable e, BaseResponse<T> response) {
        String errorMsg = ExceptionUtils.handleException(e);
        ToastUtils.showShort(errorMsg);
    }
}
