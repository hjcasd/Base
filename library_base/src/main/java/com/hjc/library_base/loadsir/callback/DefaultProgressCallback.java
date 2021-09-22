package com.hjc.library_base.loadsir.callback;

import android.content.Context;
import android.view.View;

import com.hjc.library_base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: loadSir 等待加载
 */
public class DefaultProgressCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.base_layout_default_progress;
    }

    @Override
    protected boolean onReloadEvent(Context context, View view) {
        return true;
    }
}
