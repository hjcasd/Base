package com.hjc.module_senior.widget.sticky;

import android.view.View;

/**
 * @Author: HJC
 * @Date: 2021/9/9 16:52
 * @Description: 吸附View接口
 */
public interface IStickyView {

    /**
     * 是否是吸附view
     *
     * @param view 当前view
     * @return 结果
     */
    boolean isStickyView(View view);

    /**
     * 得到吸附view的itemType
     *
     * @return itemType
     */
    int getStickViewType();
}
