package com.hjc.base.ui.login.contract;


import com.hjc.base.base.mvp.BaseView;
import com.hjc.base.ui.login.model.LoginRequest;

public interface LoginContract {

    interface View extends BaseView {
        void toMainActivity();
    }

    interface Presenter {
        void login(LoginRequest loginRequest);
    }
}
