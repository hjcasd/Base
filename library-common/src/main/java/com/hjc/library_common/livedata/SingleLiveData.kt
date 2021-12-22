package com.hjc.library_common.livedata

import androidx.annotation.MainThread
import androidx.annotation.Nullable
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.LogUtils
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @Author: HJC
 * @Date: 2021/12/22 15:04
 * @Description: 多个观察者存在时，只有一个Observer能够收到数据更新
 */
class SingleLiveData<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            LogUtils.e("Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner) { t ->
            //如果expect为true，那么将值update为false，方法整体返回true，
            //即当前Observer能够收到更新，后面如果还有订阅者，不能再收到更新通知了
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        }
    }

    override fun setValue(@Nullable value: T?) {
        //AtomicBoolean中设置的值设置为true
        mPending.set(true)
        super.setValue(value)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}