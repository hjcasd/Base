package com.hjc.module_home.ui.paging

import androidx.paging.PositionalDataSource
import com.blankj.utilcode.util.LogUtils
import com.hjc.module_home.http.entity.ConcertBean

class ConcertDataSource : PositionalDataSource<ConcertBean?>() {

    override fun loadInitial(
        params: LoadInitialParams,
        callback: LoadInitialCallback<ConcertBean?>
    ) {
        LogUtils.e("111111111")
        LogUtils.e(params.pageSize)
        callback.onResult(fetchItems(0, 20), 0, 2000)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<ConcertBean?>) {
        LogUtils.e("222222222")
        LogUtils.e(params.startPosition)
        callback.onResult(fetchItems(params.startPosition, params.loadSize))
    }

    private fun fetchItems(startPosition: Int, pageSize: Int): List<ConcertBean> {
        val list: MutableList<ConcertBean> = ArrayList()
        for (i in startPosition until startPosition + pageSize) {
            val concert = ConcertBean()
            concert.title = "title = $i"
            concert.author = "author = $i"
            list.add(concert)
        }
        return list
    }

}