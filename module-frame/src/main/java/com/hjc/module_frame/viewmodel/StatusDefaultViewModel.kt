package com.hjc.module_frame.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_common.viewmodel.KotlinViewModel
import com.hjc.module_frame.http.entity.ArticleBean
import com.hjc.module_frame.model.LoadSirModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class StatusDefaultViewModel(application: Application) : KotlinViewModel(application) {

    private val mModel = LoadSirModel()

    val listData = MutableLiveData<MutableList<ArticleBean>>()
    val refreshData = MutableLiveData<Boolean>()

    fun loadArticleList(page: Int, isShowProgress: Boolean) {
        launchWrapper({
            mModel.getArticleList(page)
        }, { result ->
            refreshData.value = true

            val data = result?.datas
            data?.let {
                if (it.size > 0) {
                    showContent()
                    listData.value = it
                } else {
                    if (page == 0) {
                        showEmpty("")
                    } else {
                        showContent()
                        ToastUtils.showShort("没有更多数据了")
                    }
                }
            }
        }, error = { e ->
            refreshData.value = true

            if (e is UnknownHostException || e is SocketTimeoutException) {
                showTimeout()
            } else {
                showError("")
            }
        }, isShowProgress = isShowProgress)
    }

}