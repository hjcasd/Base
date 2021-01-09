package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hjc.base.bean.UserBean;
import com.hjc.base.model.LiveModel;
import com.hjc.baselib.viewmodel.BaseViewModel;


public class LiveViewModel extends BaseViewModel {

    private final LiveModel liveModel;

    private final MutableLiveData<UserBean> userLiveData = new MutableLiveData<>();

    public LiveViewModel(@NonNull Application application) {
        super(application);
        liveModel = new LiveModel();
    }

    public void showData() {
        UserBean bean = liveModel.getUser();
        userLiveData.setValue(bean);
    }

    public void changeData() {
        UserBean bean = new UserBean("李四", 28, true);
        userLiveData.setValue(bean);
    }

    public void hideData() {
        UserBean bean = liveModel.getUser();
        bean.setShow(false);
        userLiveData.setValue(bean);
    }

    public MutableLiveData<UserBean> getUserLiveData() {
        return userLiveData;
    }
}
