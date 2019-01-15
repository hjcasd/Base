package com.hjc.base.http.helper;


import com.trello.rxlifecycle2.LifecycleProvider;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:48
 * @Description: 线程切换及绑定生命周期
 */
public class RxHelper {

    public static <T> ObservableTransformer<T, T> bind(LifecycleProvider provider) {
        return upstream -> {
            Observable<T> observable = upstream.compose(RxSchedulers.ioToMain())
                    .compose(provider.bindToLifecycle());
            return observable;
        };
    }
}
