package com.hjc.base.model.request;

import com.hjc.base.http.bean.BaseRequest;

public class UpdateRequest extends BaseRequest {
    private String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
