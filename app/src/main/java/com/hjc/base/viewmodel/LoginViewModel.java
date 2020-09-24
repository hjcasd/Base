package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.bean.LoginReq;
import com.hjc.base.bean.LoginResp;
import com.hjc.base.model.LoginModel;
import com.hjc.baselib.base.IModelListener;
import com.hjc.baselib.viewmodel.BaseViewModel;

public class LoginViewModel extends BaseViewModel {

    private LoginModel loginModel;

    private MutableLiveData<LoginResp> loginData = new MutableLiveData<>();
    private MutableLiveData<String> phoneData = new MutableLiveData<>();
    private MutableLiveData<String> codeData = new MutableLiveData<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginModel = new LoginModel(this);
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

        loginModel.login(loginReq, (IModelListener<LoginResp>) loginResp -> {
            loginData.setValue(loginResp);
            phoneData.setValue("");
            codeData.setValue("");
        });
    }

    public MutableLiveData<LoginResp> getLoginData() {
        return loginData;
    }

    public MutableLiveData<String> getPhoneData() {
        return phoneData;
    }

    public MutableLiveData<String> getCodeData() {
        return codeData;
    }
}
