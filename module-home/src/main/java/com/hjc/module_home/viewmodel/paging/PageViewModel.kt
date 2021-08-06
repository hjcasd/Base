package com.hjc.module_home.viewmodel.paging

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hjc.library_base.viewmodel.BaseViewModel
import com.hjc.module_home.http.entity.ConcertBean
import com.hjc.module_home.ui.paging.ConcertFactory

class PageViewModel(application: Application) : BaseViewModel(application) {

    private val concertDataSource: DataSource<Int, ConcertBean>

    val convertList: LiveData<PagedList<ConcertBean>>

    init {
        val concertFactory = ConcertFactory()
        concertDataSource = concertFactory.create()
        convertList = LivePagedListBuilder(concertFactory, 20).build()
    }

    fun invalidateDataSource() {
        concertDataSource.invalidate()
    }

}