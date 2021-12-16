package com.hjc.module_login.http.entity

import java.io.Serializable

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:25
 * @Description: 登录Bean
 */
data class LoginBean(
    var admin: Boolean,
    var chapterTops: List<String>,
    var collectIds: MutableList<String>,
    var email: String,
    var icon: String,
    var id: String,
    var nickname: String,
    var password: String,
    var token: String,
    var type: Int,
    var username: String
) : Serializable