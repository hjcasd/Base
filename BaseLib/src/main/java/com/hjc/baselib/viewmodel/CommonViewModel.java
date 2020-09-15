package com.hjc.baselib.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.hjc.baselib.model.CommonModel;

/**
 * @Author: HJC
 * @Date: 2020/6/5 16:45
 * @Description: 通用ViewModel
 */
public class CommonViewModel extends BaseViewModel<CommonModel> {

    public CommonViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected CommonModel createModel() {
        return null;
    }
}
