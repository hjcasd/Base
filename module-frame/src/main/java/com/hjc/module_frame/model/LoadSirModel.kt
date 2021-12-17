package com.hjc.module_frame.model

import com.hjc.library_common.model.CommonModel
import com.hjc.library_net.bean.BaseResponse
import com.hjc.library_net.bean.PageResponse
import com.hjc.module_frame.http.entity.ArticleBean
import com.hjc.module_frame.http.FrameApiService

class LoadSirModel : CommonModel() {

    private val mApi = getApiService(FrameApiService::class.java)

    suspend fun getArticleList( page: Int): BaseResponse<PageResponse<MutableList<ArticleBean>>> {
        return mApi.getArticleList(page)
    }

}