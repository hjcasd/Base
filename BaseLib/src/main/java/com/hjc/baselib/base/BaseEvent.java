package com.hjc.baselib.base;

public class BaseEvent {

    private final int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
