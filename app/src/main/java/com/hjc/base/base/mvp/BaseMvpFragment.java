package com.hjc.base.base.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hjc.base.base.fragment.BaseFragment;

/**
 * @Author: HJC
 * @Date: 2019/1/4 15:02
 * @Description: Fragment基类(mvp)
 */
public abstract class BaseMvpFragment<V extends BaseView, P extends BasePresenter<V>> extends BaseFragment implements View.OnClickListener {
    private P mPresenter;
    private V mView;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        if (mView == null) {
            mView = createView();
        }
        if (mPresenter != null && mView != null) {
            mPresenter.attachView(mView);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
	}

    protected abstract P createPresenter();

    protected abstract V createView();

    @Override
    public void onDestroyView() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
}
