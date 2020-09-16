package com.hjc.base.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivitySplashBinding;
import com.hjc.base.ui.MainActivity;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.viewmodel.CommonViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 启动页
 */
@Route(path = RoutePath.URL_SPLASH)
public class SplashActivity extends BaseMvmActivity<ActivitySplashBinding, CommonViewModel> {

    private Disposable disposable1;
    private Disposable disposable2;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    protected int getBindingVariable() {
        return 0;
    }

    @Override
    protected void initImmersionBar() {
        ImmersionBar.with(this)
                .init();
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        disposable1 = Observable.timer(3, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                });

        //倒计时3s
        disposable2 = Observable.intervalRange(0, 3, 0, 1, TimeUnit.SECONDS)
                .compose(RxSchedulers.ioToMain())
                .subscribe(aLong -> {
                    String time = "倒计时" + (3 - aLong) + "s";
                    mBindingView.tvTime.setText(time);
                });
    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);
    }

    @Override
    public void onSingleClick(View v) {
        if (v.getId() == R.id.tv_time) {
            if (disposable1 != null && disposable2 != null) {
                disposable1.dispose();
                disposable2.dispose();
                RouteManager.jump(RoutePath.URL_MAIN);
                finish();
            }
        }
    }

}
