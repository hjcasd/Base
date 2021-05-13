package com.hjc.base.viewmodel.list

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.hjc.base.http.RetrofitClient
import com.hjc.base.model.ArticleBean
import com.hjc.base.viewmodel.KotlinViewModel
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ListViewModel(application: Application) : KotlinViewModel(application) {

    private val listData = MutableLiveData<MutableList<ArticleBean>>()

    private val refreshData = MutableLiveData<Boolean>()

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
            }else{
                showTimeout()
            }
        }, isShowProgress = isShowProgress)
    }

    fun getListData(): MutableLiveData<MutableList<ArticleBean>> {
        return listData
    }

    fun getRefreshData(): MutableLiveData<Boolean> {
        return refreshData
    }
}