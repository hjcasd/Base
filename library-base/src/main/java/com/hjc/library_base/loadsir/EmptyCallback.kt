package com.hjc.library_base.loadsir

import com.hjc.library_base.R
import com.kingja.loadsir.callback.Callback

/**
 * @Author: HJC
 * @Date: 2020/5/15 11:13
 * @Description: loadSir 空页面
 */
class EmptyCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.base_layout_empty
    }
}