package com.hjc.base.ui.login.contract;


import com.hjc.base.base.mvp.BaseView;
import com.hjc.base.model.request.LoginReq;
import com.hjc.base.model.response.LoginResp;

public interface LoginContract {

    interface View extends BaseView {
        void toMainActivity(LoginResp loginResp);
    }

    interface Presenter {
        void login(LoginReq loginReq);
    }
}
