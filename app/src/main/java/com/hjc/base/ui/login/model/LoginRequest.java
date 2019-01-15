package com.hjc.base.ui.login.model;

/**
 * Created by HJC on 2018/3/12.
 */

public class LoginRequest {
    private String phone;
    private String verifyCode;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
