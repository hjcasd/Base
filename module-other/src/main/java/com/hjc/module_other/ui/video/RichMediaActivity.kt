package com.hjc.module_other.ui.video

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.adapter.MyFragmentPagerAdapter
import com.hjc.module_other.databinding.OtherActivityRichMediaBinding
import com.hjc.module_other.dialog.PlaneInfoDialog
import com.hjc.module_other.dialog.SeatInfoDialog
import com.hjc.module_other.ui.video.child.PictureFragment
import com.hjc.module_other.ui.video.child.VideoFragment
import com.hjc.module_other.utils.MediaViewUtils
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

    private val fragments: MutableList<Fragment> = ArrayList()

    private var isShowSeat = false
    private var isClickPlane = false
    private var isClickSeat = false

    private var flag = false

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

        fragments.add(VideoFragment.newInstance())
        fragments.add(PictureFragment.newInstance())
        fragments.add(PictureFragment.newInstance())
        fragments.add(PictureFragment.newInstance())
        fragments.add(PictureFragment.newInstance())

        val adapter =
            MyFragmentPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments)
        mBindingView.viewPager.adapter = adapter

        //参数含义:预加载n个页面,同时缓存2n + 1个页面
        mBindingView.viewPager.offscreenPageLimit = 4

        val pageCount = "1/" + fragments.size
        mBindingView.tvPageCount.text = pageCount

        isClickPlane = true
        PlaneInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.viewPager.addOnPageChangeListener(object : OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val pageCount = (position + 1).toString() + "/" + fragments.size
                mBindingView.tvPageCount.text = pageCount
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.iv_close -> finish()

            R.id.iv_plane -> {
                isShowSeat = false
                isClickSeat = false
                if (!isClickPlane) {
                    isClickPlane = true
                    mBindingView.rlRightPanel.visibility = View.GONE
                    mBindingView.ivPlane.setImageResource(R.mipmap.other_icon_plane_red)
                    mBindingView.ivSeat.setImageResource(R.mipmap.other_icon_seat_white)
                    EventManager.sendEvent(MessageEvent(EventCode.PLAY_PLANE_VIDEO, null))
                    PlaneInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
                }
            }

            R.id.iv_seat -> {
                isShowSeat = true
                isClickPlane = false
                if (!isClickSeat) {
                    isClickSeat = true
                    mBindingView.rlRightPanel.visibility = View.GONE
                    mBindingView.ivSeat.setImageResource(R.mipmap.other_icon_seat_red)
                    mBindingView.ivPlane.setImageResource(R.mipmap.other_icon_plane_white)
                    EventManager.sendEvent(MessageEvent(EventCode.PLAY_SEAT_VIDEO, null))
                    SeatInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
                }
            }

            R.id.ll_switch -> {
                if (flag) {
                    flag = false
                    mBindingView.ivEyes.setImageResource(R.mipmap.other_icon_eyes_red)
                    MediaViewUtils.showLeftView(mBindingView.llLeftPanel)
                } else {
                    flag = true
                    mBindingView.ivEyes.setImageResource(R.mipmap.other_icon_eyes_white)
                    MediaViewUtils.hideLeftView(mBindingView.llLeftPanel,  mBindingView.llFunction.width.toFloat())
                }
            }

            R.id.rl_right_panel -> {
                mBindingView.rlRightPanel.visibility = View.GONE
                if (isShowSeat) {
                    SeatInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
                } else {
                    PlaneInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
                }
            }

            else -> {
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        when (event.code) {
            EventCode.HIDE_RIGHT_PANEL -> {
                mBindingView.rlRightPanel.visibility = View.VISIBLE
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