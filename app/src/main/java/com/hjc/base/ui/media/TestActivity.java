package com.hjc.base.ui.media;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.base.ui.BaseListActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * @Author: HJC
 * @Date: 2020/1/2 17:04
 * @Description: 测试页面
 */
@Route(path = RoutePath.URL_TEST)
public class TestActivity extends BaseListActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public String getPageTitle() {
        return "测试";
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        showLoading();

        Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    showContent();
                });
    }
}
