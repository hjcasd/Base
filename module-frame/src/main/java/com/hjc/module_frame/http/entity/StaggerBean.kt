package com.hjc.module_frame.http.entity

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:25
 * @Description: 瀑布流Bean
 */
data class StaggerBean(
    var url: String = "",
    var title: String = "",
    var subTitle: String = "",
    var height: Int = 0,
    var width: Int = 0,
)
