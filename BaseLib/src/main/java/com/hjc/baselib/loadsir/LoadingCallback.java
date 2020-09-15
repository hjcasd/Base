package com.hjc.baselib.loadsir;

import android.content.Context;
import android.view.View;

import com.hjc.baselib.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: loadSir 等待加载
 */
public class LoadingCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.default_layout_loading;
    }

    @Override
    public boolean getSuccessVisible() {
        return super.getSuccessVisible();
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
