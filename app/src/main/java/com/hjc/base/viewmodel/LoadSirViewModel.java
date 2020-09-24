package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.hjc.base.bean.ArticleBean;
import com.hjc.base.http.observer.BasePageObserver;
import com.hjc.base.model.LoadSirModel;
import com.hjc.baselib.viewmodel.BaseViewModel;

import java.util.List;

public class LoadSirViewModel extends BaseViewModel {

    private LoadSirModel loadSirModel;

    private MutableLiveData<List<ArticleBean.DataBean.DatasBean>> listLiveData = new MutableLiveData<>();

    public LoadSirViewModel(@NonNull Application application) {
        super(application);
        loadSirModel = new LoadSirModel();
    }

    public void loadList(int page, boolean isFirst) {
        loadSirModel.load(page).subscribe(new BasePageObserver<ArticleBean>(this, isFirst) {
            @Override
            public void onSuccess(ArticleBean result) {
                ArticleBean.DataBean dataBean = result.getData();
                if (dataBean != null) {
                    List<ArticleBean.DataBean.DatasBean> dataList = dataBean.getDatas();
                    if (dataList != null && dataList.size() > 0) {
                        listLiveData.setValue(dataList);
                    }
                }
            }
        });
    }

    public MutableLiveData<List<ArticleBean.DataBean.DatasBean>> getListLiveData() {
        return listLiveData;
    }
}
