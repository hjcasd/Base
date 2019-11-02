package com.hjc.base.ui.login.contract;

import com.hjc.base.model.request.LoginReq;
import com.hjc.base.model.response.LoginResp;
import com.hjc.baselib.mvp.BaseView;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:23
 * @Description: 登录contract
 */
public interface LoginContract {

    interface View extends BaseView {
        void toMainActivity(LoginResp loginResp);
    }

    interface Presenter {
        void login(LoginReq loginReq);
    }
}
