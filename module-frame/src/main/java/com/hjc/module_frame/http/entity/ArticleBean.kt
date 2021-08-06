package com.hjc.module_frame.http.entity

import java.io.Serializable

/**
 * @Author: HJC
 * @Date: 2021/1/9 15:25
 * @Description: 列表文章Bean
 */
data class ArticleBean(
    var apkLink: String,
    var author: String,
    var chapterId: Int,
    var chapterName: String,
    var collect: Boolean,
    var courseId: Int,
    var desc: String,
    var envelopePic: String,
    var fresh: Boolean,
    var id: Int,
    var link: String,
    var niceDate: String,
    var origin: String,
    var prefix: String,
    var projectLink: String,
    var publishTime: Long,
    var superChapterId: Int,
    var superChapterName: String,
    var shareUser: String,
    var title: String,
    var type: Int,
    var userId: Int,
    var visible: Int,
    var zan: Int,
    var isSelected: Boolean = false
) : Serializable
