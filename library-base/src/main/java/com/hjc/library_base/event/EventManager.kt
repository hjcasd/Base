package com.hjc.library_base.event

import org.greenrobot.eventbus.EventBus

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:57
 * @Description: EventBus管理类
 */
object EventManager {

    /**
     * 注册EventBus
     */
    fun register(subscriber: Any) {
        if (!EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().register(subscriber)
        }
    }

    /**
     * 解绑EventBus
     */
    fun unregister(subscriber: Any) {
        if (EventBus.getDefault().isRegistered(subscriber)) {
            EventBus.getDefault().unregister(subscriber)
        }
    }

    /**
     * 发送非粘性消息
     */
    fun sendEvent(event: MessageEvent<*>) {
        EventBus.getDefault().post(event)
    }

    /**
     * 发送粘性消息
     */
    fun sendStickyEvent(event: MessageEvent<*>) {
        EventBus.getDefault().postSticky(event)
    }

}