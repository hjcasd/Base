package com.hjc.library_common.router.path

/**
 * @Author: HJC
 * @Date: 2021/6/20 18:55
 * @Description: Frame模块路由
 */
object RouteFramePath {

    private const val MODULE_FRAME = "/module_frame"

    // Frame页面
    const val URL_FRAME_ACTIVITY = "$MODULE_FRAME/frame/activity"

    // FrameFragment
    const val URL_FRAME_FRAGMENT = "$MODULE_FRAME/frame/fragment"

    // LoadSir页面
    const val URL_LOAD_SIR = "$MODULE_FRAME/loadSir"

    // EventBus页面
    const val URL_EVENT_POST = "$MODULE_FRAME/event/post"
    const val URL_EVENT_RECEIVE = "$MODULE_FRAME/event/receive"

    // Zxing页面
    const val URL_QR_CODE = "$MODULE_FRAME/zxing/show"
    const val URL_SCAN_CODE = "$MODULE_FRAME/zxing/scan"

}