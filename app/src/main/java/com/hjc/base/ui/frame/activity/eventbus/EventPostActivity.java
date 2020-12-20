package com.hjc.base.ui.frame.activity.eventbus;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.constant.EventCode;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityEventPostBinding;
import com.hjc.base.utils.helper.RouteManager;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.event.EventManager;
import com.hjc.baselib.event.MessageEvent;
import com.hjc.baselib.viewmodel.CommonViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * @Author: HJC
 * @Date: 2019/7/26 11:23
 * @Description: EventBus发送粘性事件
 */
@Route(path = RoutePath.URL_EVENT_POST)
public class EventPostActivity extends BaseMvmActivity<ActivityEventPostBinding, CommonViewModel> {
    /*
     * 使用详解:
     *
     * EventBus在要接受消息的页面使用EventBus.getDefault().register(this)方法订阅消息,
     * 在onDestroy()中使用EventBus.getDefault().unregister(this)解除消息订阅,
     * 在要接收消息的地方使用 @Subscribe(threadMode = ThreadMode.MAIN)进行订阅,
     * 在要发送消息的地方使用 EventBus.getDefault().post(-)发送消息
     *
     */

    /*
     * ThreadMode(4种)
     *
     * POSTING:如果使用事件处理函数指定了线程模型为PostThread，那么该事件在哪个线程发布出来的，事件处理函数就会在这个线程中运行，
     * 也就是说(发布事件和接收事件在同一个线程)。在线程模型为PostThread的事件处理函数中尽量避免执行耗时操作，因为它会阻塞事件的传递，甚至有可能会引起ANR。
     *
     * MAIN:<如果使用事件处理函数指定了线程模型为MainThread，那么不论事件是在哪个线程中发布出来的，该事件处理函数都会在UI线程中执行。
     * 该方法可以用来更新UI，但是(不能处理耗时操作)。
     *
     * BACKGROUND:如果使用事件处理函数指定了线程模型为BackgroundThread，那么如果事件是在UI线程中发布出来的，那么该事件处理函数就会在新的线程中运行，
     * 如果事件本来就是子线程中发布出来的，那么该事件处理函数直接在发布事件的线程中执行。在此事件处理函数中禁止进行UI更新操作。
     *
     * ASYNC:如果使用事件处理函数指定了线程模型为Async，那么无论事件在哪个线程发布，该事件处理函数都会在新建的子线程中执行。
     * 同样，此事件处理函数中禁止进行UI更新操作。
     */

    /**
     * 模拟粘性事件(就是在发送事件之后再订阅该事件也能收到该事件EventBus.getDefault().postSticky())
     * 在接收的地方使用@Subscribe(threadMode = ThreadMode.MAIN, sticky = true)进行订阅
     */

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_post;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventManager.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(MessageEvent<String> event) {
        if (event.getCode() == EventCode.B) {
            String data = event.getData();
            mBindingView.tvContent.setText(data);
        }
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
            case R.id.btn_post:
                EventManager.sendStickyEvent(new MessageEvent<>(EventCode.A, "这是发送的粘性消息"));
                ToastUtils.showShort("发送粘性消息成功");
                break;

            case R.id.btn_receive:
                RouteManager.jump(RoutePath.URL_EVENT_RECEIVE);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }
}
