package com.hjc.base.ui.login.presenter;


import com.hjc.base.base.mvp.BasePresenter;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.ui.login.contract.LoginContract;
import com.hjc.base.ui.login.model.LoginRequest;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;


public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(LoginRequest request) {
        getView().showLoading();

        Observable.timer(2, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    getView().hideLoading();
                    getView().toMainActivity();
                });
    }
}
