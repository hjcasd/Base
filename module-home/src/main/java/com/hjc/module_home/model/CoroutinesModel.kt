package com.hjc.module_home.model

import com.hjc.library_common.model.CommonModel
import com.hjc.module_home.http.HomeApiService

class CoroutinesModel : CommonModel() {

    private val mApi = getApiService(HomeApiService::class.java)

    suspend fun getGankIoData(): Any? {
        return mApi.getGankIoData("GanHuo", "All", 1, 20)
    }

    suspend fun getHotFilm(): Any? {
        return mApi.getHotFilm()
    }

}