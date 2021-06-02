package com.hjc.library_net.utils

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:50
 * @Description: 线程切换类(订阅在IO线程, 观察在主线程)
 */
object RxSchedulers {

    fun <T> ioToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream: Observable<T> ->
            upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}