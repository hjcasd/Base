package com.hjc.base.ui.senior.touch;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityTouchCancelBinding;
import com.hjc.baselib.activity.BaseActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 验证ACTION_CANCEL事件产生的时机
 */
@Route(path = RoutePath.URL_TOUCH_CANCEL)
public class TouchCancelActivity extends BaseActivity<ActivityTouchCancelBinding, CommonViewModel> {

    /*
     * 当在ScrollView中嵌套一个Button时,如果按下Button不放,并上下滑动后,
     * 当ScrollView滑动后会拦截事件,并会出现ACTION_CANCEL事件
     *
     * ACTION_CANCEL: 3
     *
     * ACTION_MOVE: 2
     *
     * ACTION_DOWN: 1
     *
     */

    @Override
    public int getLayoutId() {
        return R.layout.activity_touch_cancel;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void addListeners() {
        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.e("TouchCancelActivity: dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("TouchCancelActivity: onTouchEvent");
        return super.onTouchEvent(event);
    }
}
