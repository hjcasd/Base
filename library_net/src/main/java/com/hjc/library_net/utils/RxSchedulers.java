package com.hjc.library_net.utils;

import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:50
 * @Description: 线程切换类(订阅在IO线程, 观察在主线程)
 */
public class RxSchedulers {

    private RxSchedulers() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Observable 切换到主线程
     */
    public static <T> ObservableTransformer<T, T> ioToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Flowable 切换到主线程
     */
    public static <T> FlowableTransformer<T, T> flowableToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * Maybe 切换到主线程
     */
    public static <T> MaybeTransformer<T, T> maybeToMain() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
