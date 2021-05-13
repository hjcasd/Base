package com.hjc.baselib.event

/**
 * @Author: HJC
 * @Date: 2019/1/4 14:55
 * @Description: 封装用于EventBus传递消息类
 */
class MessageEvent<T>(private var code: Int) {
    private var data: T? = null

    constructor(code: Int, data: T) : this(code) {
        this.data = data
    }

    fun getCode(): Int {
        return this.code
    }

    fun getData(): T? {
        return this.data
    }
}