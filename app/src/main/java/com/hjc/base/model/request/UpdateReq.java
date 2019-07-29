package com.hjc.base.model.request;

import com.hjc.base.http.bean.BaseRequest;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:30
 * @Description: 更新请求bean
 */
public class UpdateReq extends BaseRequest {
    private String appType;

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }
}
