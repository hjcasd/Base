package com.hjc.base.ui.home.page;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.bean.ConcertBean;

import java.util.ArrayList;
import java.util.List;


public class ConcertDataSource extends PositionalDataSource<ConcertBean> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull final LoadInitialCallback<ConcertBean> callback) {
        LogUtils.e("111111111");
        LogUtils.e(params.pageSize);
        callback.onResult(fetchItems(0, 20), 0, 2000);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<ConcertBean> callback) {
        LogUtils.e("222222222");
        LogUtils.e(params.startPosition);
        callback.onResult(fetchItems(params.startPosition, params.loadSize));
    }

    private List<ConcertBean> fetchItems(int startPosition, int pageSize) {
        List<ConcertBean> list = new ArrayList<>();
        for (int i = startPosition; i < startPosition + pageSize; i++) {
            ConcertBean concert = new ConcertBean();
            concert.setTitle("title = " + i);
            concert.setAuthor("author = " + i);
            list.add(concert);
        }
        return list;
    }

}