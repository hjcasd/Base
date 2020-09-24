package com.hjc.base.ui.frame.activity.eventbus;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.constant.EventCode;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.databinding.ActivityEventReceiveBinding;
import com.hjc.baselib.activity.BaseMvmActivity;
import com.hjc.baselib.event.EventManager;
import com.hjc.baselib.event.MessageEvent;
import com.hjc.baselib.viewmodel.CommonViewModel;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Author: HJC
 * @Date: 2019/7/26 11:23
 * @Description: EventBus发送非粘性事件
 */
@Route(path = RoutePath.URL_EVENT_RECEIVE)
public class EventReceiveActivity extends BaseMvmActivity<ActivityEventReceiveBinding, CommonViewModel> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_receive;
    }

    @Override
    protected CommonViewModel getViewModel() {
        return null;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventManager.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handleEvent(MessageEvent<String> event) {
        if (event.getCode() == EventCode.A) {
            String data = event.getData();
            mBindingView.tvContent.setText(data);
        }
    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        if (v.getId() == R.id.btn_post) {
            EventManager.sendStickyEvent(new MessageEvent(EventCode.B, "我是发送的非粘性消息"));
            ToastUtils.showShort("发送非粘性消息成功");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }
}
