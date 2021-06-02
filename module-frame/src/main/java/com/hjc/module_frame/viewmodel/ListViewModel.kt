package com.hjc.module_frame.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_common.viewmodel.KotlinViewModel
import com.hjc.library_net.RetrofitClient
import com.hjc.library_net.model.ArticleBean
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ListViewModel(application: Application) : KotlinViewModel(application) {

    val listData = MutableLiveData<MutableList<ArticleBean>>()
    val refreshData = MutableLiveData<Boolean>()

    fun loadArticleList(page: Int, isShowProgress: Boolean) {
        launchWrapper({
            RetrofitClient.getApiService1().getArticleList(page)
        }, { result ->
            refreshData.value = true

            val data = result?.datas
            data?.let {
                if (it.size > 0) {
                    showContent()
                    listData.value = it
                } else {
                    if (page == 0) {
                        showEmpty()
                    } else {
                        showContent()
                        ToastUtils.showShort("没有更多数据了")
                    }
                }
            }
        }, { e ->
            refreshData.value = true

            if (e is UnknownHostException || e is SocketTimeoutException) {
                showError()
            } else {
                showTimeout()
            }
        }, isShowProgress = isShowProgress)
    }

}