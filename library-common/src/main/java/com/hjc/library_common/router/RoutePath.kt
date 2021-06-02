package com.hjc.library_common.router

/**
 * @Author: HJC
 * @Date: 2021/1/8 16:24
 * @Description: 管理路由路径
 */
object RoutePath {

    // 主模块
    object Main {
        private const val MODULE_MAIN = "/module_main"

        // Splash页面
        const val URL_SPLASH = "$MODULE_MAIN/splash"

        // 主界面
        const val URL_MAIN = "$MODULE_MAIN/main"

        // Web页面
        const val URL_WEB = "$MODULE_MAIN/web"
    }


    // 首页模块
    object Home {
        private const val MODULE_HOME = "/module_home"

        // Home页面
        const val URL_HOME_ACTIVITY = "$MODULE_HOME/home"

        // HomeFragment
        const val URL_HOME_FRAGMENT = "$MODULE_HOME/fragment"

        // DataBinding页面
        const val URL_DATA_BINDING = "$MODULE_HOME/dataBinding"

        // LiveData页面
        const val URL_LIVE_DATA = "$MODULE_HOME/liveData"

        // BindingAdapter页面
        const val URL_BINDING_ADAPTER = "$MODULE_HOME/bindingAdapter"

        // Paging页面
        const val URL_PAGING = "$MODULE_HOME/paging"

        // Room页面
        const val URL_ROOM = "$MODULE_HOME/room"

        // Coroutines页面
        const val URL_COROUTINES = "$MODULE_HOME/coroutines"
    }


    // 框架模块
    object Frame {
        private const val MODULE_FRAME = "/module_frame"

        // Frame页面
        const val URL_FRAME_ACTIVITY = "$MODULE_FRAME/frame"

        // FrameFragment
        const val URL_FRAME_FRAGMENT = "$MODULE_FRAME/fragment"

        // LoadSir页面
        const val URL_LOAD_SIR = "$MODULE_FRAME/list"

        // EventBus页面
        const val URL_EVENT_POST = "$MODULE_FRAME/event/post"
        const val URL_EVENT_RECEIVE = "$MODULE_FRAME/event/receive"

        // Zxing页面
        const val URL_QR_CODE = "$MODULE_FRAME/zxing/show"
        const val URL_SCAN_CODE = "$MODULE_FRAME/zxing/scan"
    }


    // 高级模块
    object Senior {
        private const val MODULE_SENIOR = "/module_senior"

        // Senior页面
        const val URL_SENIOR_ACTIVITY = "$MODULE_SENIOR/senior"

        // SeniorFragment
        const val URL_SENIOR_FRAGMENT = "$MODULE_SENIOR/fragment"


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
         * MotionLayout页面
         */
        const val URL_THEME = "$MODULE_SENIOR/theme"
        const val URL_MOTION = "$MODULE_SENIOR/motion"
    }


    // 其他模块
    object Other {
        private const val MODULE_OTHER = "/module_other"

        // Other页面
        const val URL_OTHER_ACTIVITY = "$MODULE_OTHER/other"

        // OtherFragment
        const val URL_OTHER_FRAGMENT = "$MODULE_OTHER/fragment"

        // Login页面
        const val URL_LOGIN = "$MODULE_OTHER/login"
    }

}