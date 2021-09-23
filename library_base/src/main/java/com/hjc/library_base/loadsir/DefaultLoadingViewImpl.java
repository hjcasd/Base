package com.hjc.library_base.loadsir;

import android.content.Context;

import com.hjc.library_base.base.ILoadingView;
import com.hjc.library_base.dialog.LoadingDialog;

/**
 * @Author: HJC
 * @Date: 2021/6/25 10:01
 * @Description: 默认加载框实现
 */
public class DefaultLoadingViewImpl implements ILoadingView {

    private final LoadingDialog mLoadingDialog;

    public DefaultLoadingViewImpl(Context context){
        mLoadingDialog = new LoadingDialog.Builder(context)
                .setMessage("加载中...")
                .setCancelable(true)
                .setCancelOutside(false)
                .create();
    }

    @Override
    public void showLoading() {
        if (mLoadingDialog != null){
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoading() {
        if (mLoadingDialog != null){
            mLoadingDialog.dismissDialog();
        }
    }

}
