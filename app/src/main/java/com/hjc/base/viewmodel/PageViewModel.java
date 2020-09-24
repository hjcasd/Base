package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.hjc.base.bean.ConcertBean;
import com.hjc.base.ui.home.page.ConcertFactory;
import com.hjc.baselib.viewmodel.CommonViewModel;

public class PageViewModel extends CommonViewModel {

    private final LiveData<PagedList<ConcertBean>> convertList;
    private DataSource<Integer, ConcertBean> concertDataSource;

    public PageViewModel(@NonNull Application application) {
        super(application);

        ConcertFactory concertFactory = new ConcertFactory();
        concertDataSource = concertFactory.create();
        convertList = new LivePagedListBuilder<>(concertFactory, 20).build();
    }

    public void invalidateDataSource() {
        concertDataSource.invalidate();
    }

    public LiveData<PagedList<ConcertBean>> getConvertList() {
        return convertList;
    }
}
