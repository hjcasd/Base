package com.hjc.library_net.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:50
 * @Description: 线程切换类(订阅在IO线程, 观察在主线程)
 */
object RxSchedulers {

    /**
     * Observable 切换到主线程
     */
    fun <T> ioToMain(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { upstream: Observable<T> ->
            upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * Flowable 切换到主线程
     */
    fun <T> flowableToMain(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream: Flowable<T> ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * Maybe 切换到主线程
     */
    fun <T> maybeToMain(): MaybeTransformer<T, T> {
        return MaybeTransformer { upstream: Maybe<T> ->
            upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}