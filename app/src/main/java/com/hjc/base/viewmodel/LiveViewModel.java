package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hjc.base.bean.UserBean;
import com.hjc.base.model.LiveModel;
import com.hjc.baselib.viewmodel.BaseViewModel;


public class LiveViewModel extends BaseViewModel<LiveModel> {

    // 一个 LiveData对象通常存储在ViewModel对象中，并通过getter方法访问
    private MutableLiveData<UserBean> userLiveData = new MutableLiveData<>();

    public LiveViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected LiveModel createModel() {
        return new LiveModel();
    }

    public void showData() {
        UserBean bean = mModel.getUser();
        userLiveData.setValue(bean);
    }

    public void changeData() {
        UserBean bean = new UserBean("李四", 28, true);
        userLiveData.setValue(bean);
    }

    public void hideData() {
        UserBean bean = mModel.getUser();
        bean.setShow(false);
        userLiveData.setValue(bean);
    }

    // getter
    public MutableLiveData<UserBean> getUserLiveData() {
        return userLiveData;
    }
}
