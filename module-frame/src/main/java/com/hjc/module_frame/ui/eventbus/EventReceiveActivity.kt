package com.hjc.module_frame.ui.eventbus

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.event.EventManager
import com.hjc.library_common.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.router.RoutePath
import com.hjc.library_base.viewmodel.CommonViewModel
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_frame.R
import com.hjc.module_frame.databinding.FrameActivityEventReceiveBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author: HJC
 * @Date: 2019/7/26 11:23
 * @Description: EventBus发送非粘性事件
 */
@Route(path = RoutePath.Frame.URL_EVENT_RECEIVE)
class EventReceiveActivity : BaseActivity<FrameActivityEventReceiveBinding, CommonViewModel>() {

    override fun getLayoutId(): Int {
        return  R.layout.frame_activity_event_receive
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.frame_color)
    }

   override fun initData(savedInstanceState: Bundle?) {
        EventManager.register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun handleEvent(event: MessageEvent<String?>) {
        if (event.getKey() === EventCode.EVENT_POST_CODE) {
            val data: String? = event.getData()
            mBindingView.tvContent.text = data
        }
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }
        })
    }

    override fun onSingleClick(v: View?) {
        if (v?.id == R.id.btn_post) {
            EventManager.sendStickyEvent(MessageEvent(EventCode.EVENT_RECEIVE_CODE, "我是发送的非粘性消息"))
            ToastUtils.showShort("发送非粘性消息成功")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.unregister(this)
    }
}