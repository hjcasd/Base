package com.hjc.library_base.base;

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

    void showEmpty(String msg);

    void showError(String msg);

    void showTimeout();

    MutableLiveData<BaseActionEvent> getActionLiveData();
}
