package com.hjc.library_net.bean

import java.io.Serializable

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:18
 * @Description: 列表通用Json
 */
data class PageResponse<T>(
    var datas: T,
    var curPage: Int,
    var offset: Int,
    var over: Boolean,
    var pageCount: Int,
    var size: Int,
    var total: Int
) : Serializable