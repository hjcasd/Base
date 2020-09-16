package com.hjc.baselib.model;


/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 网络回调
 */
public interface IModelListener<T> {
    void loadSuccess(T t);

    default void loadFail() {
    }
}
