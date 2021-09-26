package com.hjc.module_other.ui.video

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.adapter.MyFragmentPagerAdapter
import com.hjc.module_other.databinding.OtherActivityVideoBinding
import com.hjc.module_other.ui.video.fragment.PictureFragment
import com.hjc.module_other.ui.video.fragment.VideoFragment
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
        fragments.add(VideoFragment.newInstance())
        fragments.add(PictureFragment.newInstance())

        val adapter =
            MyFragmentPagerAdapter(supportFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments)
        mBindingView.viewPager.adapter = adapter

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
                mBindingView.planeInfoView.visibility = View.VISIBLE
            }

            R.id.iv_seat -> {
                mBindingView.seatInfoView.visibility = View.VISIBLE
            }

            R.id.ll_switch -> ToastUtils.showShort("开关")

            else -> {
            }
        }
    }

}