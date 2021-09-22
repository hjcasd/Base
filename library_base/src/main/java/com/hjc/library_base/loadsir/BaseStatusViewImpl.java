package com.hjc.library_base.loadsir;

import android.annotation.SuppressLint;
import android.view.View;

import com.hjc.library_base.base.IStatusView;
import com.hjc.library_base.loadsir.callback.DefaultEmptyCallback;
import com.hjc.library_base.loadsir.callback.DefaultErrorCallback;
import com.hjc.library_base.loadsir.callback.DefaultProgressCallback;
import com.hjc.library_base.loadsir.callback.DefaultTimeoutCallback;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: HJC
 * @Date: 2021/6/25 10:02
 * @Description: 默认状态布局实现
 */
public class BaseStatusViewImpl implements IStatusView {

    private LoadService<?> mLoadService;

    @Override
    public void setLoadSir(View view, Callback.OnReloadListener listener) {
        LoadSir loadSir = LoadSir.getDefault();
        mLoadService = loadSir.register(view, listener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void showContent() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mLoadService != null) {
                        mLoadService.showSuccess();
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void showProgress() {
        if (mLoadService != null) {
            mLoadService.showCallback(DefaultProgressCallback.class);
        }
    }

    @SuppressLint("CheckResult")
    @Override
    public void showEmpty(String msg) {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mLoadService != null) {
                        mLoadService.showCallback(DefaultEmptyCallback.class);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void showError(String msg) {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mLoadService != null) {
                        mLoadService.showCallback(DefaultErrorCallback.class);
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void showTimeout() {
        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (mLoadService != null) {
                        mLoadService.showCallback(DefaultTimeoutCallback.class);
                    }
                });
    }
}
