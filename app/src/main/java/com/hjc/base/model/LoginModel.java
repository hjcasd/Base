package com.hjc.base.model;

import com.hjc.base.bean.LoginReq;
import com.hjc.base.bean.LoginResp;
import com.hjc.base.http.RetrofitClient;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.http.bean.BaseResponse;
import com.hjc.baselib.model.BaseModel;

import io.reactivex.Observable;

public class LoginModel extends BaseModel {

    public Observable<BaseResponse<LoginResp>> login(LoginReq loginReq) {
        return RetrofitClient.getInstance().getAPI()
                .login(loginReq)
                .compose(RxSchedulers.ioToMain());
    }
}
