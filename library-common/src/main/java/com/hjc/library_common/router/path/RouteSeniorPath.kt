package com.hjc.library_common.router.path

/**
 * @Author: HJC
 * @Date: 2021/6/20 18:55
 * @Description: Senior模块路由
 */
object RouteSeniorPath {

    private const val MODULE_SENIOR = "/module_senior"

    // Senior页面
    const val URL_SENIOR_ACTIVITY = "$MODULE_SENIOR/senior/activity"

    // SeniorFragment
    const val URL_SENIOR_FRAGMENT = "$MODULE_SENIOR/senior/fragment"


    /**
     * Touch页面
     */
    const val URL_TOUCH = "$MODULE_SENIOR/touch"
    const val URL_TOUCH_DEFAULT = "$MODULE_SENIOR/touch/default"
    const val URL_TOUCH_CANCEL = "$MODULE_SENIOR/touch/cancel"
    const val URL_TOUCH_INTERCEPT = "$MODULE_SENIOR/touch/intercept"


    /**
     * View页面
     */
    const val URL_VIEW = "$MODULE_SENIOR/view"
    const val URL_VIEW_BASE = "$MODULE_SENIOR/view/base"
    const val URL_VIEW_CANVAS = "$MODULE_SENIOR/view/canvas"
    const val URL_VIEW_PICTURE = "$MODULE_SENIOR/view/picture"
    const val URL_VIEW_PATH = "$MODULE_SENIOR/view/path"
    const val URL_VIEW_RADAR = "$MODULE_SENIOR/view/radar"


    /**
     * Theme页面
     */
    const val URL_THEME = "$MODULE_SENIOR/theme"


    /**
     * Animation页面
     */
    const val URL_ANIMATION = "$MODULE_SENIOR/anim"
    const val URL_ANIMATION_OBJECT = "$MODULE_SENIOR/anim/object"
    const val URL_ANIMATION_VALUE = "$MODULE_SENIOR/anim/value"
    const val URL_ANIMATION_SET = "$MODULE_SENIOR/anim/set"
    const val URL_ANIMATION_XML = "$MODULE_SENIOR/anim/xml"
    const val URL_ANIMATION_INTERPOLATOR = "$MODULE_SENIOR/anim/interpolator"


    /**
     * Scroll页面
     */
    const val URL_SCROLL = "$MODULE_SENIOR/scroll"
}