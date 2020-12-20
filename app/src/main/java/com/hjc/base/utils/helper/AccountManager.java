package com.hjc.base.utils.helper;

import com.blankj.utilcode.util.SPUtils;

/**
 * @Author: HJC
 * @Date: 2019/2/20 15:39
 * @Description: 账户管理类
 */
public class AccountManager {
    private static AccountManager mInstance;

    private static final String KEY_IS_LOGIN = "isLogin";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_COOKIE = "cookie";

    private AccountManager() {
    }

    public static AccountManager getInstance() {
        if (mInstance == null) {
            synchronized (AccountManager.class) {
                if (mInstance == null) {
                    mInstance = new AccountManager();
                }
            }
        }
        return mInstance;
    }

    public void init(boolean isLogin, String username) {
        setLogin(isLogin);
        setUsername(username);
    }

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public String getUsername() {
        return SPUtils.getInstance().getString(KEY_USERNAME);
    }

    public void setUsername(String username) {
        SPUtils.getInstance().put(KEY_USERNAME, username);
    }

    /**
     * 获取cookie
     */
    public String getCookie() {
        return SPUtils.getInstance().getString(KEY_COOKIE);
    }

    public void setCookie(String cookie) {
        SPUtils.getInstance().put(KEY_COOKIE, cookie);
    }

    /**
     * 用户是否登录
     *
     * @return 是否登录
     */
    public boolean isLogin() {
        return SPUtils.getInstance().getBoolean(KEY_IS_LOGIN);
    }

    public void setLogin(boolean isLogin) {
        SPUtils.getInstance().put(KEY_IS_LOGIN, isLogin);
    }

    /**
     * 清除账户信息
     */
    public void clear() {
        setLogin(false);
        setUsername("");
        setCookie("");
    }
}
