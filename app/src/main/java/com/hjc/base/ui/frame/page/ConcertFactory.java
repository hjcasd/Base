package com.hjc.base.ui.frame.page;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.hjc.base.bean.ConcertBean;


public class ConcertFactory extends DataSource.Factory<Integer, ConcertBean> {
    private MutableLiveData<ConcertDataSource> mSourceData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource<Integer, ConcertBean> create() {
        ConcertDataSource source = new ConcertDataSource();
        mSourceData.postValue(source);
        return source;
    }
}
