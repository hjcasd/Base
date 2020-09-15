package com.hjc.baselib.base;

public class BaseActionEvent extends BaseEvent {

    public static final int START_LOADING_DIALOG = 1;

    public static final int DISMISS_LOADING_DIALOG = 2;

    public static final int SHOW_LOADING = 3;

    public static final int SHOW_CONTENT = 4;

    public static final int SHOW_EMPTY = 5;

    public static final int SHOW_ERROR = 6;

    public BaseActionEvent(int action) {
        super(action);
    }

}
