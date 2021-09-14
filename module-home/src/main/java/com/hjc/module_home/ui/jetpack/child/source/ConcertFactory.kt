package com.hjc.module_home.ui.jetpack.child.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hjc.module_home.http.entity.ConcertBean

class ConcertFactory : DataSource.Factory<Int, ConcertBean>() {

    private val mSourceData: MutableLiveData<ConcertDataSource> =
        MutableLiveData<ConcertDataSource>()

    override fun create(): DataSource<Int, ConcertBean> {
        val source = ConcertDataSource()
        mSourceData.postValue(source)
        return source as DataSource<Int, ConcertBean>
    }
}