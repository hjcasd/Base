package com.hjc.module_senior.http

import com.chad.library.adapter.base.entity.MultiItemEntity

data class ScrollBean(var name: String = "", var type: Int = 0) : MultiItemEntity {

    override val itemType: Int
        get() = type

    companion object{
        const val TYPE_TITLE = 0
        const val TYPE_CONTENT = 1
    }
}