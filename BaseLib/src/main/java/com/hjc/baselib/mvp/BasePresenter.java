package com.hjc.baselib.mvp;

import java.lang.ref.WeakReference;

/**
 * @Author: HJC
 * @Date: 2019/1/4 15:03
 * @Description: Presenter层基类
 */
public abstract class BasePresenter<V> {

    /**
     * 持有UI接口的弱引用
     */
    private WeakReference<V> mViewRef;

    /**
     * 绑定(在onCreate()中调用)
     */
    void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public V getView() {
        return mViewRef.get();
    }

    /**
     * 解绑(在onDestroy方法中调用，防止内存泄漏)
     */
    void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
