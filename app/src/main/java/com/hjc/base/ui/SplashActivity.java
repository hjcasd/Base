package com.hjc.base.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.http.helper.RxHelper;
import com.hjc.base.http.helper.RxSchedulers;
import com.hjc.baselib.activity.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 启动页
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;

    private Disposable disposable1;
    private Disposable disposable2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .init();
    }

    @Override
    public void initView() {

    }

    @SuppressLint("CheckResult")
    @Override
    public void initData(Bundle savedInstanceState) {
        disposable1 = Observable.timer(3, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                });

        //倒计时3s
        disposable2 = Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
                .compose(RxHelper.bind(this))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tvTime.setText("倒计时" + (3 - aLong) + "s");
                    }
                });
    }

    @Override
    public void addListeners() {
        tvTime.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        if (v.getId() == R.id.tv_time) {
            if (disposable1 != null && disposable2 != null) {
                disposable1.dispose();
                disposable2.dispose();
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }
    }

}
