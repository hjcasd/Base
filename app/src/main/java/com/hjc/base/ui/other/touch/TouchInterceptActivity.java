package com.hjc.base.ui.other.touch;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 验证事件拦截逻辑
 */
@Route(path = RoutePath.URL_TOUCH_INTERCEPT)
public class TouchInterceptActivity extends BaseActivity {

    /*
     * 重写ViewGroup的onInterceptTouchEvent(ev),直接返回true,表示拦截事件,不会将事件传递给子View
     * 重写ViewGroup的onTouchEvent(ev),直接返回true,表示将事件交给onTouchEvent(ev)来消费
     *
     */

    @BindView(R.id.title_bar)
    TitleBar titleBar;


    @Override
    public int getLayoutId() {
        return R.layout.activity_touch_intercept;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void addListeners() {
        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.e("TouchInterceptActivity: dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("TouchInterceptActivity: onTouchEvent");
        return super.onTouchEvent(event);
    }
}
