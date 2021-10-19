package com.hjc.library_common.router.path

/**
 * @Author: HJC
 * @Date: 2021/6/20 18:55
 * @Description: Other模块路由
 */
object RouteOtherPath {

    /**
     * Other路径
     */
    private const val MODULE_OTHER = "/module_other"

    /**
     * Other页面
     */
    const val URL_OTHER_ACTIVITY = "$MODULE_OTHER/other/activity"

    /**
     * OtherFragment
     */
    const val URL_OTHER_FRAGMENT = "$MODULE_OTHER/other/fragment"

    /**
     * Dialog页面
     */
    const val URL_DIALOG = "$MODULE_OTHER/dialog"

    /**
     * Login页面
     */
    const val URL_LOGIN = "$MODULE_OTHER/login"

    /**
     * Audio页面
     */
    const val URL_AUDIO = "$MODULE_OTHER/audio"

    /**
     * Scroll页面
     */
    const val URL_SCROLL = "${MODULE_OTHER}/scroll"
    const val URL_SCROLL_TOPPING = "${MODULE_OTHER}/scroll/topping"
    const val URL_SCROLL_INDICATOR = "${MODULE_OTHER}/scroll/indicator"

    /**
     * 富媒体页面
     */
    const val URL_RICH_MEDIA = "$MODULE_OTHER/richMedia"

}