package com.hjc.base.ui.login.presenter;

import com.hjc.base.http.RetrofitClient1;
import com.hjc.base.http.helper.RxHelper;
import com.hjc.base.http.observer.BaseProgressObserver;
import com.hjc.base.model.request.LoginReq;
import com.hjc.base.model.response.LoginResp;
import com.hjc.base.ui.login.LoginActivity;
import com.hjc.base.ui.login.contract.LoginContract;
import com.hjc.baselib.mvp.BasePresenter;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:24
 * @Description: 登录presenter
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(LoginReq loginReq) {
        LoginActivity loginActivity = (LoginActivity) getView();

        RetrofitClient1.getInstance().getAPI()
                .login(loginReq)
                .compose(RxHelper.bind(loginActivity))
                .subscribe(new BaseProgressObserver<LoginResp>(loginActivity.getSupportFragmentManager()) {

                    @Override
                    public void onSuccess(LoginResp result) {
                        result.setPhoneNo(loginReq.getPhoneNo());
                        getView().toMainActivity(result);
                    }
                });
    }
}
