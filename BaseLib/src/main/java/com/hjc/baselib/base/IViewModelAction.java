package com.hjc.baselib.base;

import androidx.lifecycle.MutableLiveData;


public interface IViewModelAction {

    void startLoading();

    void dismissLoading();

    void showLoading();

    void showContent();

    void showEmpty();

    void showError();

    MutableLiveData<BaseActionEvent> getActionLiveData();
}
