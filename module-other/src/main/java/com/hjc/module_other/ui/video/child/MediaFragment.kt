package com.hjc.module_other.ui.video.child

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.adapter.MyFragmentPagerAdapter
import com.hjc.module_other.databinding.OtherFragmentMediaBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: 富媒体fragment
 */
class MediaFragment : BaseFragment<OtherFragmentMediaBinding, CommonViewModel>() {

    private val fragments: MutableList<Fragment> = ArrayList()

    companion object {
        fun newInstance(videoUrl: String, imageUrls: ArrayList<String>, type: Int): MediaFragment {
            val fragment = MediaFragment()
            val bundle = Bundle()
            bundle.putString("videoUrl", videoUrl)
            bundle.putStringArrayList("imageUrls", imageUrls)
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.other_fragment_media
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        EventManager.register(this)

        arguments?.let {
            val videoUrl = it.getString("videoUrl", "")
            val imageUrls = it.getStringArrayList("imageUrls")
            val type = it.getInt("type", 0)

            fragments.add(VideoFragment.newInstance(videoUrl, type))
            if (!imageUrls.isNullOrEmpty()) {
                for (imageUrl in imageUrls) {
                    fragments.add(PictureFragment.newInstance(imageUrl))
                }
            }

            val adapter = MyFragmentPagerAdapter(childFragmentManager, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments)
            mBindingView.viewPager.adapter = adapter

            //参数含义:预加载n个页面,同时缓存2n + 1个页面
            mBindingView.viewPager.offscreenPageLimit = 4

            val pageCount = "1/" + fragments.size
            mBindingView.tvPageCount.text = pageCount
        }
    }

    override fun addListeners() {
        mBindingView.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                val pageCount = (position + 1).toString() + "/" + fragments.size
                mBindingView.tvPageCount.text = pageCount
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    override fun onSingleClick(v: View?) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        when (event.code) {
            // 显示或者隐藏页码
            EventCode.SHOW_PAGE_COUNT -> {
                val isShow = event.data as Boolean
                mBindingView.tvPageCount.visibility = if (isShow) View.VISIBLE else View.GONE
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