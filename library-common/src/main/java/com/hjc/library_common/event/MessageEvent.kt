package com.hjc.library_common.event

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: 封装用于EventBus传递消息类
 */
class MessageEvent<T>(private var key: String) {
    private var data: T? = null

    constructor(key: String, data: T) : this(key) {
        this.data = data
    }

    fun getKey(): String {
        return this.key
    }

    fun getData(): T? {
        return this.data
    }
}