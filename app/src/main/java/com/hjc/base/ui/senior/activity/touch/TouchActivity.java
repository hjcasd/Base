package com.hjc.base.ui.senior.activity.touch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.hjc.base.R;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityTouchBinding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.viewmodel.CommonViewModel;

/**
 * @Author: HJC
 * @Date: 2019/10/15 16:58
 * @Description: 事件分发
 */
@Route(path = RoutePath.URL_TOUCH)
public class TouchActivity extends BaseMvmActivity<ActivityTouchBinding, CommonViewModel> {

    /*
     * 触摸事件 ->Activity ->Window - DecorView -> ViewGroup -> View
     *
     *
     * Activity: dispatchTouchEvent()
     * 1. ACTION_DOWN: 手指按下事件
     * 2. onUserInteraction: 空实现
     * 3. getWindow().superDispatchTouchEvent(ev): 将事件交给依附的Window处理
     *
     *
     * Window: superDispatchTouchEvent()
     * 1. superDispatchTouchEvent(): 如果返回true,则Activity的dispatchTouchEvent直接返回true,表示事件已经被Activity消费掉了;
     * 如果返回false,表示该事件未被消费,则会调用Activity的onTouchEvent(),并将onTouchEvent()的返回值作为Activity的dispatchTouchEvent()的返回值
     *
     *
     * 2. Window是一个抽象类,唯一实现类是PhoneWindow,所以getWindow()拿到的Window对象即是PhoneWindow,即 getWindow().superDispatchTouchEvent(ev)
     * 是一个抽象方法,最终会调用PhoneWindow的superDispatchTouchEvent(),而在PhoneWindow的superDispatchTouchEvent()中调用的又是DecorView中的superDispatchTouchEvent(),
     * PhoneWindow中持有DecorView的一个实例
     *
     *
     * DecorView:
     * 3. DecorView是Activity的最顶层View,如果调用setContentView添加布局,那么就会将布局添加到DecorView上,DecorView继承自FrameLayout,
     * 而FrameLayout自身是没有superDispatchTouchEvent()的,但FrameLayout是继承自ViewGroup的,而ViewGroup是有superDispatchTouchEvent()实现的即dispatchTouchEvent(),
     * 所以最终调用的是ViewGroup的dispatchTouchEvent()
     *
     *
     * ViewGroup:
     * dispatchTouchEvent(ev): 分发事件, 流程主要做了3件事,首先判断是否需要拦截当前事件,然后在当前的ViewGroup中找到用户真正点击的View,最后将事件分发到该View上
     * onInterceptTouchEvent(ev): 拦截事件
     * onTouchEvent(ev):
     *
     *
     * View:
     * dispatchTouchEvent(ev): 如果返回true,表示当前事件被消费 , 如果返回false,表示事件未被消费
     * onTouchEvent(ev): 处理当前触摸事件的逻辑,包括ACTION_DOWN,ACTION_MOVE,ACTION_UP等
     */


    @Override
    public int getLayoutId() {
        return R.layout.activity_touch;
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
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_touch_default:
                RouteManager.jump(RoutePath.URL_TOUCH_DEFAULT);
                break;

            case R.id.btn_touch_cancel:
                RouteManager.jump(RoutePath.URL_TOUCH_CANCEL);
                break;

            case R.id.btn_touch_intercept:
                RouteManager.jump(RoutePath.URL_TOUCH_INTERCEPT);
                break;

            default:
                break;
        }
    }

}
