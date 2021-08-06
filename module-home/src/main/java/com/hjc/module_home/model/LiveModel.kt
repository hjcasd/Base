package com.hjc.module_home.model

import com.hjc.library_base.model.BaseModel
import com.hjc.module_home.http.entity.UserBean

class LiveModel : BaseModel() {

    val user: UserBean
        get() = UserBean("张三", 20, true)
}