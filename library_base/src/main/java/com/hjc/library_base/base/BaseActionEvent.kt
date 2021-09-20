package com.hjc.library_base.base

/**
 * @Author: HJC
 * @Date: 2020/12/30 14:01
 * @Description: 基础事件
 */
class BaseActionEvent(action: Int,val msg: String = "") : BaseEvent(action) {
    companion object {
        const val SHOW_LOADING_DIALOG = 1
        const val DISMISS_LOADING_DIALOG = 2
        const val SHOW_PROGRESS = 3
        const val SHOW_CONTENT = 4
        const val SHOW_EMPTY = 5
        const val SHOW_ERROR = 6
        const val SHOW_TIMEOUT = 7
    }
}