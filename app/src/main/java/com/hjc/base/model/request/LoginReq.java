package com.hjc.base.model.request;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:24
 * @Description: 登录请求bean
 */
public class LoginReq {
    private String phoneNo;
    private String verifyCode;

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
}
