package com.hjc.base.model;

import com.hjc.base.bean.LoginReq;
import com.hjc.base.bean.LoginResp;
import com.hjc.base.http.RetrofitClient;
import com.hjc.base.viewmodel.LoginViewModel;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.http.observer.BaseProgressObserver;
import com.hjc.baselib.model.BaseModel;
import com.hjc.baselib.model.IModelListener;

public class LoginModel extends BaseModel {
    private LoginViewModel mViewModel;

    public LoginModel(LoginViewModel viewModel){
        mViewModel = viewModel;
    }

    public void login(LoginReq loginReq, IModelListener listener) {
        RetrofitClient.getInstance().getAPI()
                .login(loginReq)
                .compose(RxSchedulers.ioToMain())
                .subscribe(new BaseProgressObserver<LoginResp>(mViewModel) {

                    @Override
                    public void onSuccess(LoginResp result) {
                        listener.loadSuccess(result);
                    }
                });
    }
}
