package com.hjc.baselib.mvp;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.hjc.baselib.activity.BaseActivity;


/**
 * @Author: HJC
 * @Date: 2019/1/4 15:00
 * @Description: Activity基类(mvp)
 */
public abstract class BaseMvpActivity<V extends IBaseView, P extends BasePresenter<V>> extends BaseActivity {
    private P mPresenter;
    private V mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mView == null) {
            mView = createView();
        }
        if (mPresenter != null && mView != null) {
            mPresenter.attachView(mView);
        }
        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();

    protected abstract V createView();

    protected P getPresenter(){
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }
}
