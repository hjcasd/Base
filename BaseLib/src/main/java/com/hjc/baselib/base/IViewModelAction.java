package com.hjc.baselib.base;

import androidx.lifecycle.MutableLiveData;

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:57
 * @Description: ViewModel基础接口
 */
public interface IViewModelAction {

    void showLoading();

    void dismissLoading();

    void showProgress();

    void showContent();

    void showEmpty();

    void showError();

    void showTimeout();

    MutableLiveData<BaseActionEvent> getActionLiveData();
}
