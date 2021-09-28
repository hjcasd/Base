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
import com.hjc.module_other.databinding.OtherActivityVideoBinding
import com.hjc.module_other.dialog.PlaneInfoDialog
import com.hjc.module_other.dialog.SeatInfoDialog
import com.hjc.module_other.ui.video.fragment.PictureFragment
import com.hjc.module_other.ui.video.fragment.VideoFragment
import com.hjc.module_other.utils.ViewUtils
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:29
 * @Description: 视频播放页
 */
@Route(path = RouteOtherPath.URL_VIDEO)
class VideoActivity : BaseActivity<OtherActivityVideoBinding, CommonViewModel>() {

    private val fragments: MutableList<Fragment> = ArrayList()

    override fun getLayoutId(): Int {
        return R.layout.other_activity_video
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initView() {
        super.initView()
        val layoutParams = mBindingView.clRoot.layoutParams as FrameLayout.LayoutParams
        layoutParams.rightMargin = BarUtils.getStatusBarHeight()
        mBindingView.clRoot.layoutParams = layoutParams
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
                mBindingView.ivPlane.setImageResource(R.mipmap.other_icon_plane_red)
                PlaneInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
            }

            R.id.iv_seat -> {
                SeatInfoDialog.newInstance().setGravity(Gravity.END).showDialog(supportFragmentManager)
                mBindingView.ivSeat.setImageResource(R.mipmap.other_icon_seat_red)
            }

            R.id.ll_switch1 -> {
                mBindingView.llSwitch1.visibility = View.GONE
                ViewUtils.showLeftView(mBindingView.llFunction)
            }

            R.id.ll_switch2 -> {
                ViewUtils.hideLeftView(mBindingView.llFunction, mBindingView.llSwitch1)
            }

            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        when (event.code) {
            EventCode.HIDE_PLANE_VIEW -> {
                mBindingView.ivPlane.setImageResource(R.mipmap.other_icon_plane_white)
            }

            EventCode.HIDE_SEAT_VIEW -> {
                mBindingView.ivSeat.setImageResource(R.mipmap.other_icon_seat_white)
            }

            else -> {

            }
        }
    }

}