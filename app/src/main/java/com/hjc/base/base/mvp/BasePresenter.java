package com.hjc.base.base.mvp;

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
    protected WeakReference<V> mViewRef;

    /**
     * 绑定
     * 在onCreate()中调用
     *
     * @param view
     */
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public V getView() {
        return mViewRef.get();
    }

    /**
     * 解绑
     * 在onDestroy方法中调用，防止内存泄漏
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
