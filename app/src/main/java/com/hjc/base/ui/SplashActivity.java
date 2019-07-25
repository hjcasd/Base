package com.hjc.base.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.http.helper.RxSchedulers;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class SplashActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> startActivity(new Intent(SplashActivity.this, MainActivity.class)));
//                .subscribe(aLong -> startActivity(new Intent(SplashActivity.this, LoginActivity.class)));
    }

    @Override
    public void addListeners() {

    }

    @Override
    public void onSingleClick(View v) {

    }

}
