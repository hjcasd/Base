package com.hjc.library_base.base

import android.view.View
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/4/28 15:22
 * @Description: 自定义状态布局需实现该接口
 */
interface IStatusView {

    fun setLoadSir(view: View?, listener: Callback.OnReloadListener?)

    /**
     * 显示内容
     */
    fun showContent()

    /**
     * 显示进度
     */
    fun showProgress()

    /**
     * 显示空页面
     */
    fun showEmpty(msg: String)

    /**
     * 加载失败
     */
    fun showError(msg: String)

    /**
     * 网络超时
     */
    fun showTimeout()
}