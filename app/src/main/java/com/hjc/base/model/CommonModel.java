package com.hjc.base.model;

import com.hjc.library_base.model.BaseModel;
import com.hjc.library_net.SmartHttp;

/**
 * @Author: HJC
 * @Date: 2021/9/22 20:24
 * @Description: 通用Model
 */
public class CommonModel extends BaseModel {

    public <T> T getApiService(Class<T> apiService) {
        return SmartHttp.getInstance().getRetrofit().create(apiService);
    }

}
