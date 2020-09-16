package com.hjc.base.model;

import com.hjc.base.bean.ArticleBean;
import com.hjc.base.http.RetrofitHelper;
import com.hjc.base.http.observer.BasePageObserver;
import com.hjc.base.viewmodel.LoadSirViewModel;
import com.hjc.baselib.http.RxSchedulers;
import com.hjc.baselib.model.BaseModel;
import com.hjc.baselib.model.IModelListener;

public class LoadSirModel extends BaseModel {
    private LoadSirViewModel mViewModel;

    public LoadSirModel(LoadSirViewModel viewModel){
        mViewModel = viewModel;
    }

    public void load(int page, boolean isFirst, IModelListener<ArticleBean> listener) {
        RetrofitHelper.getInstance().getAPI()
                .getArticleList(page, null)
                .compose(RxSchedulers.ioToMain())
                .subscribe(new BasePageObserver<ArticleBean>(mViewModel, isFirst) {

                    @Override
                    public void onSuccess(ArticleBean result) {
                        listener.loadSuccess(result);
                    }
                });
    }
}
