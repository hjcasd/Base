package com.hjc.base.ui.frame.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.hjc.base.R;
import com.hjc.base.base.activity.BaseActivity;
import com.hjc.base.base.event.EventManager;
import com.hjc.base.base.event.MessageEvent;
import com.hjc.base.constant.EventCode;
import com.hjc.base.constant.RoutePath;
import com.hjc.base.utils.SchemeUtils;
import com.hjc.base.widget.bar.TitleBar;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

@Route(path = RoutePath.URL_EVENT_POST)
public class EventPostActivity extends BaseActivity {
    @BindView(R.id.title_bar)
    TitleBar titleBar;
    @BindView(R.id.btn_post)
    Button btnPost;
    @BindView(R.id.btn_receive)
    Button btnReceive;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_event_post;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData(Bundle savedInstanceState) {
        EventManager.register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleEvent(MessageEvent<String> event) {
        if (event.getCode() == EventCode.B) {
            String data = event.getData();
            tvContent.setText(data);
        }
    }

    @Override
    public void addListeners() {
        btnPost.setOnClickListener(this);
        btnReceive.setOnClickListener(this);

        titleBar.setOnViewLeftClickListener(view -> finish());
    }

    @Override
    public void onSingleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_post:
                EventManager.sendStickyEvent(new MessageEvent(EventCode.A, "这是发送的粘性消息"));
                ToastUtils.showShort("发送粘性消息成功");
                break;

            case R.id.btn_receive:
                SchemeUtils.jump(RoutePath.URL_EVENT_RECEIVE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.unregister(this);
    }
}
