package com.hjc.baselib.mvp;

import com.blankj.utilcode.util.ToastUtils;

/**
 * @Author: HJC
 * @Date: 2019/1/4 15:03
 * @Description: View层基类
 */
public interface IBaseView {
    default void showLoading() {

    }

    default void hideLoading() {

    }

    default void showContent() {

    }

    default void showError() {

    }

    default void showEmpty() {

    }

    default void showNoNetwork() {

    }

    default void showToast(String message) {
        ToastUtils.showShort(message);
    }
}
