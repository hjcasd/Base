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
 * @Description: 事件分发
 */
@Route(path = RoutePath.URL_TOUCH)
public class TouchActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    TitleBar titleBar;

    /**
     * 触摸事件 ->Activity ->Window - DecorView -> ViewGroup -> View
     * <p>
     * Activity: dispatchTouchEvent()
     * 1. ACTION_DOWN: 手指按下事件
     * 2. onUserInteraction: 空实现
     * 3. getWindow().superDispatchTouchEvent(ev): 将事件交给依附的Window处理
     * <p>
     * Window: superDispatchTouchEvent()
     * 1. superDispatchTouchEvent(): 如果返回true,则Activity的dispatchTouchEvent直接返回true,表示事件已经被Activity消费掉了;
     * 如果返回false,表示该事件未被消费,则会调用Activity的onTouchEvent(),并将onTouchEvent()的返回值作为Activity的dispatchTouchEvent()的返回值
     * <p>
     * 2. Window是一个抽象类,唯一实现类是PhoneWindow,所以getWindow()拿到的Window对象即是PhoneWindow,即 getWindow().superDispatchTouchEvent(ev)
     * 是一个抽象方法,最终会调用PhoneWindow的superDispatchTouchEvent(),而在PhoneWindow的superDispatchTouchEvent()中调用的又是DecorView中的superDispatchTouchEvent(),
     * PhoneWindow中持有DecorView的一个实例
     * <p>
     * DecorView:
     * 3. DecorView是Activity的最顶层View,如果调用setContentView添加布局,那么就会将布局添加到DecorView上,DecorView继承自FrameLayout,
     * 而FrameLayout自身是没有superDispatchTouchEvent()的,但FrameLayout是继承自ViewGroup的,而ViewGroup是有superDispatchTouchEvent()实现的即dispatchTouchEvent(),
     * 所以最终调用的是ViewGroup的dispatchTouchEvent()
     * <p>
     * <p>
     * ViewGroup:
     * dispatchTouchEvent(ev): 分发事件, 流程主要做了3件事,首先判断是否需要拦截当前事件,然后在当前的ViewGroup中找到用户真正点击的View,最后将事件分发到该View上
     * onInterceptTouchEvent(ev): 拦截事件
     * onTouchEvent(ev):
     * <p>
     * View:
     * dispatchTouchEvent(ev): 如果返回true,表示当前事件被消费 , 如果返回false,表示事件未被消费
     * onTouchEvent(ev): 处理当前触摸事件的逻辑,包括ACTION_DOWN,ACTION_MOVE,ACTION_UP等
     * <p>
     * <p>
     * <p>
     * <p>
     * 事件默认传递流程:
     * Activity: dispatchTouchEvent -> MyViewGroup: dispatchTouchEvent -> MyViewGroup: onInterceptTouchEvent(默认返回false,即不拦截事件,将事件分发给子View)
     * -> MyView: dispatchTouchEvent -> MyView: onTouchEvent(默认返回false,即不消费事件) -> MyViewGroup: onTouchEvent(默认返回false,即不消费事件)
     * -> Activity: onTouchEvent
     */


    @Override
    public int getLayoutId() {
        return R.layout.activity_touch;
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
        LogUtils.e("Activity: dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.e("Activity: onTouchEvent");
        return super.onTouchEvent(event);
    }
}
