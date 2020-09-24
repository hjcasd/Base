package com.hjc.base.model;

import com.hjc.base.bean.ArticleBean;
import com.hjc.base.http.RetrofitHelper;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.model.BaseModel;

import io.reactivex.Observable;

public class LoadSirModel extends BaseModel {

    public Observable<ArticleBean> load(int page) {
        return RetrofitHelper.getInstance().getAPI()
                .getArticleList(page, null)
                .compose(RxSchedulers.ioToMain());
    }
}
