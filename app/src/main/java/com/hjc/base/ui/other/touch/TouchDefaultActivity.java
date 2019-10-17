package com.hjc.base.ui.other.touch;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.widget.bar.TitleBar;

import butterknife.BindView;

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 验证事件默认的传递流程
 */
@Route(path = RoutePath.URL_TOUCH_DEFAULT)
public class TouchDefaultActivity extends BaseActivity {

    /*
     * 事件默认传递流程:
     *
     * ACTION_DOWN:
     * Activity: dispatchTouchEvent -> MyViewGroup: dispatchTouchEvent -> MyViewGroup: onInterceptTouchEvent(默认返回false,即不拦截事件,将事件分发给子View)
     * -> MyView: dispatchTouchEvent -> MyView: onTouchEvent(默认返回false,即不消费事件) -> MyViewGroup: onTouchEvent(默认返回false,即不消费事件)
     * -> Activity: onTouchEvent
     *
     * ACTION_MOVE:
     * Activity: dispatchTouchEvent -> Activity: onTouchEvent
     *
     *
     * 注意: 同一事件序列(例如手指按下后的ACTION_DOWN和ACTION_MOVE即为一个事件序列),如果子View(包括ViewGroup)没有消费该事件,那么后续的事件不会再传递到子View中
     */

    @BindView(R.id.title_bar)
    TitleBar titleBar;


    @Override
    public int getLayoutId() {
        return R.layout.activity_touch_default;
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
        LogUtils.e("TouchDefaultActivity: dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("TouchDefaultActivity: onTouchEvent");
        return super.onTouchEvent(event);
    }
}
