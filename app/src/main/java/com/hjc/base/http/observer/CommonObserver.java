package com.hjc.base.http.observer;

import com.blankj.utilcode.util.ToastUtils;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:52
 * @Description: 通用的Observer
 */
public abstract class CommonObserver<T> extends BaseObserver<T> {
    @Override
    public void onFailure(String errorMsg) {
        ToastUtils.showShort(errorMsg);
    }

}
