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
import com.hjc.module_other.utils.MediaViewUtils
import com.hjc.module_other.view.FunctionView
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
                showVideoAndPicture()
                MediaViewUtils.hideRightView(mBindingView.seatInfoView, mBindingView.rlArrow)
            }

            override fun onSeatClick() {
                type = 1
                showVideoAndPicture()
                MediaViewUtils.hideRightView(mBindingView.planeInfoView, mBindingView.rlArrow)
            }

            override fun onEyeStateChanged(state: Int) {
                if (state == 0) {
                    mBindingView.ivClose.visibility = View.VISIBLE
                    mBindingView.rlArrow.visibility = View.VISIBLE
                    EventManager.sendEvent(MessageEvent(EventCode.SHOW_PAGE_COUNT, true))
                } else {
                    mBindingView.ivClose.visibility = View.GONE
                    mBindingView.rlArrow.visibility = View.GONE
                    mBindingView.planeInfoView.visibility = View.GONE
                    mBindingView.seatInfoView.visibility = View.GONE
                    EventManager.sendEvent(MessageEvent(EventCode.SHOW_PAGE_COUNT, false))
                }
            }

        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.iv_close -> finish()

            R.id.rl_arrow -> {
                showRightPanelView()
            }

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

    /**
     * 显示右边面板
     */
    private fun showRightPanelView() {
        if (type == 0) {
            MediaViewUtils.showRightView(mBindingView.planeInfoView, mBindingView.rlArrow)
        } else {
            MediaViewUtils.showRightView(mBindingView.seatInfoView, mBindingView.rlArrow)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        when (event.code) {

            // 显示右侧面板
            EventCode.SHOW_RIGHT_PANEL -> {
                val type = event.data as Int
                if (type == 0) {
                    MediaViewUtils.showRightView(mBindingView.planeInfoView, mBindingView.rlArrow)
                } else {
                    MediaViewUtils.showRightView(mBindingView.seatInfoView, mBindingView.rlArrow)
                }
            }

            // 隐藏右侧面板
            EventCode.HIDE_RIGHT_PANEL -> {
                val type = event.data as Int
                if (type == 0) {
                    MediaViewUtils.hideRightView(mBindingView.planeInfoView, mBindingView.rlArrow)
                } else {
                    MediaViewUtils.hideRightView(mBindingView.seatInfoView, mBindingView.rlArrow)
                }
            }

            // 显示所有面板
            EventCode.SHOW_ALL_VIEW -> {
                showRightPanelView()
                mBindingView.functionView.visibility = View.VISIBLE
                EventManager.sendEvent(MessageEvent(EventCode.SHOW_PAGE_COUNT, true))
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