package com.hjc.library_base.base;

/**
 * @Author: HJC
 * @Date: 2021/1/9 14:44
 * @Description: 事件基类
 */
public class BaseEvent {

    private final int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
