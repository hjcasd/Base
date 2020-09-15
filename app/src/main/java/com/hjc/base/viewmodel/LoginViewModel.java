package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.bean.LoginReq;
import com.hjc.base.bean.LoginResp;
import com.hjc.base.http.RetrofitClient;
import com.hjc.base.model.LoginModel;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.http.observer.BaseProgressObserver;
import com.hjc.baselib.viewmodel.BaseViewModel;

public class LoginViewModel extends BaseViewModel<LoginModel> {

    // 一个 LiveData对象通常存储在ViewModel对象中，并通过getter方法访问
    private MutableLiveData<LoginResp> loginData = new MutableLiveData<>();
    private MutableLiveData<String> phoneData = new MutableLiveData<>();
    private MutableLiveData<String> codeData = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected LoginModel createModel() {
        return new LoginModel();
    }

    public void login() {
        if (StringUtils.isEmpty(phoneData.getValue())){
            ToastUtils.showShort("请输入手机号");
            return;
        }
        if (StringUtils.isEmpty(codeData.getValue())){
            ToastUtils.showShort("请输入验证码");
            return;
        }

        LoginReq loginReq = new LoginReq();
        loginReq.setPhoneNo(phoneData.getValue());
        loginReq.setVerifyCode(codeData.getValue());
        loginReq.setProductCode("BFFQ");

        RetrofitClient.getInstance().getAPI()
                .login(loginReq)
                .compose(RxSchedulers.ioToMain())
                .subscribe(new BaseProgressObserver<LoginResp>(this) {

                    @Override
                    public void onSuccess(LoginResp result) {
                        loginData.setValue(result);

                        phoneData.setValue("");
                        codeData.setValue("");
                    }
                });
    }

    // getter
    public MutableLiveData<LoginResp> getLoginData() {
        return loginData;
    }

    // getter
    public MutableLiveData<String> getPhoneData() {
        return phoneData;
    }

    // getter
    public MutableLiveData<String> getCodeData() {
        return codeData;
    }

}
