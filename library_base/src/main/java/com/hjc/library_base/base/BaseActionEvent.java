package com.hjc.library_base.base;

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:46
 * @Description: 基础事件
 */
public class BaseActionEvent extends BaseEvent {

    public static final int SHOW_LOADING_DIALOG = 1;

    public static final int DISMISS_LOADING_DIALOG = 2;

    public static final int SHOW_PROGRESS = 3;

    public static final int SHOW_CONTENT = 4;

    public static final int SHOW_EMPTY = 5;

    public static final int SHOW_ERROR = 6;

    public static final int SHOW_TIMEOUT = 7;

    private String message = "";

    public BaseActionEvent(int action) {
        super(action);
    }

    public BaseActionEvent(int action, String message) {
        super(action);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
