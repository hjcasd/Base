package com.hjc.library_base.event

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: EventBus传递消息类
 */
data class MessageEvent<T>(
    var code: String,
    var data: T? = null
)