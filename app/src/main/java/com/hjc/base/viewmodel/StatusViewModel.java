package com.hjc.base.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.bean.ArticleBean;
import com.hjc.base.http.RetrofitHelper;
import com.hjc.base.http.observer.BasePageObserver;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.viewmodel.CommonViewModel;

import java.util.List;

public class StatusViewModel extends CommonViewModel {

    // 一个 LiveData对象通常存储在ViewModel对象中，并通过getter方法访问
    private MutableLiveData<List<ArticleBean.DataBean.DatasBean>> listLiveData = new MutableLiveData<>();

    public StatusViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadList(int page, boolean isFirst) {
        RetrofitHelper.getInstance().getAPI()
                .getArticleList(page, null)
                .compose(RxSchedulers.ioToMain())
                .subscribe(new BasePageObserver<ArticleBean>(this, isFirst) {

                    @Override
                    public void onSuccess(ArticleBean result) {
                        if (result != null) {
                            ArticleBean.DataBean dataBean = result.getData();
                            if (dataBean != null) {
                                LogUtils.e("当前页码: " + dataBean.getCurPage());
                                List<ArticleBean.DataBean.DatasBean> dataList = dataBean.getDatas();
                                if (dataList != null && dataList.size() > 0) {
                                    listLiveData.setValue(dataList);
                                }
                            }
                        }
                    }

                });
    }

    // getter
    public MutableLiveData<List<ArticleBean.DataBean.DatasBean>> getListLiveData() {
        return listLiveData;
    }

}
