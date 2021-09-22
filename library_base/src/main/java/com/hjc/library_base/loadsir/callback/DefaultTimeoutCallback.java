package com.hjc.library_base.loadsir.callback;

import com.hjc.library_base.R;
import com.kingja.loadsir.callback.Callback;

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: loadSir 网络超时
 */
public class DefaultTimeoutCallback extends Callback {

    @Override
    protected int onCreateView() {
        return R.layout.base_layout_default_timeout;
    }
}
