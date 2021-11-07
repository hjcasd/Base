package com.hjc.module_other.ui.video

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityRichMediaBinding
import com.hjc.module_other.ui.video.child.MediaFragment
import com.hjc.module_other.ui.video.view.FunctionView
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 视频播放页
 */
@Route(path = RouteOtherPath.URL_RICH_MEDIA)
class RichMediaActivity : BaseActivity<OtherActivityRichMediaBinding, CommonViewModel>() {

    /**
     * 0:点击飞机,播放飞机视频, 1:点击舱位,播放舱位视频
     */
    private var type = 0

    private var isFirstIn = true

    override fun getLayoutId(): Int {
        return R.layout.other_activity_rich_media
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initView() {
        super.initView()
        val statusBarHeight = BarUtils.getStatusBarHeight()
        if (statusBarHeight > 80) {
            val layoutParams = mBindingView.clRoot.layoutParams as FrameLayout.LayoutParams
            layoutParams.rightMargin = statusBarHeight
            mBindingView.clRoot.layoutParams = layoutParams
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        EventManager.register(this)
        showVideoAndPicture()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.functionView.setOnFunctionClickListener(object : FunctionView.OnFunctionClickListener {

            override fun onPlaneClick() {
                type = 0
                mBindingView.cabinInfoView.hideAll()
                showVideoAndPicture()
            }

            override fun onSeatClick() {
                type = 1
                mBindingView.planeInfoView.hideAll()
                showVideoAndPicture()
            }

            override fun onEyeStateChanged(state: Int) {
                if (state == 0) {
                    mBindingView.ivClose.visibility = View.VISIBLE
                    if (type == 0) {
                        mBindingView.planeInfoView.showArrow()
                    } else {
                        mBindingView.cabinInfoView.showArrow()
                    }
                    EventManager.sendEvent(MessageEvent(EventCode.SHOW_FUNCTION_VIEW, true))
                } else {
                    mBindingView.ivClose.visibility = View.GONE
                    if (type == 0) {
                        mBindingView.planeInfoView.hideAll()
                    } else {
                        mBindingView.cabinInfoView.hideAll()
                    }
                    EventManager.sendEvent(MessageEvent(EventCode.SHOW_FUNCTION_VIEW, false))
                }
            }

        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.iv_close -> finish()

            else -> {
            }
        }
    }

    /**
     * 显示视频和图片
     */
    private fun showVideoAndPicture() {
        val videoUrl: String
        val imageUrls = ArrayList<String>()
        if (type == 0) {
            videoUrl = "https://v-cdn.zjol.com.cn/280443.mp4"
            imageUrls.add("http://pic1.win4000.com/wallpaper/b/58ca58d35d719.jpg")
            imageUrls.add("http://pic1.win4000.com/wallpaper/5/58d1e4522374c.jpg")
        } else {
            videoUrl = "https://v-cdn.zjol.com.cn/276982.mp4"
            imageUrls.add("http://pic1.win4000.com/wallpaper/5/58d21cc8f12c9.jpg")
            imageUrls.add("http://pic1.win4000.com/wallpaper/3/58d0c78e390e5.jpg")
        }
        val fragment = MediaFragment.newInstance(videoUrl, imageUrls, type)
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fl_content, fragment).commit()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        when (event.code) {
            // 显示右侧面板
            EventCode.SHOW_RIGHT_PANEL -> {
                val type = event.data as Int
                if (type == 0) {
                    if (isFirstIn) {
                        isFirstIn = false
                        mBindingView.functionView.visibility = View.VISIBLE
                        EventManager.sendEvent(MessageEvent(EventCode.SHOW_FUNCTION_VIEW, true))
                    }
                    mBindingView.planeInfoView.showPanel()
                } else {
                    mBindingView.cabinInfoView.showPanel()
                }
            }

            // 隐藏右侧面板
            EventCode.HIDE_RIGHT_PANEL -> {
                val type = event.data as Int
                if (type == 0) {
                    mBindingView.planeInfoView.hidePanel()
                } else {
                    mBindingView.cabinInfoView.hidePanel()
                }
            }

            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.unregister(this)
    }

}