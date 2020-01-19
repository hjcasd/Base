package com.hjc.baselib.mvp;

import com.blankj.utilcode.util.ToastUtils;

/**
 * @Author: HJC
 * @Date: 2019/1/4 15:03
 * @Description: View层基类
 */
public interface IBaseView {

    void showLoading();

    void showContent();

    void showError();

    void showEmpty();

    void showNoNetwork();

    default void showToast(String message) {
        ToastUtils.showShort(message);
    }
}
