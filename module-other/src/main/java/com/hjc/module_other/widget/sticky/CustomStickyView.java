package com.hjc.module_other.widget.sticky;

import android.view.View;

/**
 * @Author: HJC
 * @Date: 2021/9/9 16:52
 * @Description: 自定义StickyView
 */
public class CustomStickyView implements IStickyView {

    @Override
    public boolean isStickyView(View view) {
        return (Boolean) view.getTag();
    }

    @Override
    public int getStickViewType() {
        return 0;
    }
}
