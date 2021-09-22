package com.hjc.library_net.observer;

import androidx.annotation.NonNull;

import com.blankj.utilcode.util.ToastUtils;
import com.hjc.library_base.viewmodel.BaseViewModel;
import com.hjc.library_net.bean.BaseResponse;
import com.hjc.library_net.exception.ExceptionUtils;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的带进度的Observer
 */
public class CommonPageObserver<T> extends BasePageObserver<T> {

    public CommonPageObserver(BaseViewModel baseViewModel) {
        super(baseViewModel);
    }

    public CommonPageObserver(BaseViewModel baseViewModel, boolean isShowProgress) {
        super(baseViewModel, isShowProgress);
    }

    @Override
    public void onSuccess(T result) {

    }

    @Override
    public void onFailure(@NonNull Throwable e, BaseResponse<T> baseResponse) {
        String errorMsg = ExceptionUtils.handleException(e);
        ToastUtils.showShort(errorMsg);
    }
}
