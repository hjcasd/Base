package com.hjc.library_common.router.path

/**
 * @Author: HJC
 * @Date: 2021/6/20 18:55
 * @Description: Home模块路由
 */
object RouteHomePath {

    /**
     * Home路径
     */
    private const val MODULE_HOME = "/module_home"

    /**
     * Home页面
     */
    const val URL_HOME_ACTIVITY = "$MODULE_HOME/home/activity"

    /**
     * HomeFragment
     */
    const val URL_HOME_FRAGMENT = "$MODULE_HOME/home/fragment"

    /**
     * JetPack页面
     */
    const val URL_JETPACK = "$MODULE_HOME/jetpack"
    const val URL_JET_DATA_BINDING = "$MODULE_HOME/jetpack/dataBinding"
    const val URL_JET_LIVE_DATA = "$MODULE_HOME/jetpack/liveData"
    const val URL_JET_BINDING_ADAPTER = "$MODULE_HOME/jetpack/bindingAdapter"
    const val URL_JET_PAGING = "$MODULE_HOME/jetpack/paging"
    const val URL_JET_ROOM = "$MODULE_HOME/jetpack/room"

    /**
     * Coroutines页面
     */
    const val URL_COROUTINES = "$MODULE_HOME/coroutines"


    /**
     * Compose页面
     */
    const val URL_COMPOSE = "$MODULE_HOME/compose"

}