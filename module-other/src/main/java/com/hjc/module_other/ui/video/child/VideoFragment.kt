package com.hjc.module_other.ui.video.child

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.metadata.Metadata
import com.google.android.exoplayer2.metadata.MetadataOutput
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.video.VideoListener
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherFragmentVideoBinding
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: 展示视频
 */
class VideoFragment : BaseFragment<OtherFragmentVideoBinding, CommonViewModel>() {

    private var player: SimpleExoPlayer? = null

    companion object {
        fun newInstance(): VideoFragment {
            return VideoFragment()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.other_fragment_video
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        EventManager.register(this)

        //设置播放器
        player = SimpleExoPlayer.Builder(mContext).build()
        mBindingView.playerView.player = player
        mBindingView.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        //播放视频
        val videoUrl = "https://v-cdn.zjol.com.cn/280443.mp4"
        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()
    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            player?.stop()
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun handleEvent(event: MessageEvent<*>) {
        when (event.code) {
            EventCode.PLAY_PLANE_VIDEO -> {
                val videoUrl = "https://v-cdn.zjol.com.cn/280443.mp4"
                val mediaItem = MediaItem.fromUri(videoUrl)
                player?.setMediaItem(mediaItem)
                player?.prepare()
                player?.play()
            }

            EventCode.PLAY_SEAT_VIDEO -> {
                val videoUrl = "https://v-cdn.zjol.com.cn/276984.mp4"
                val mediaItem = MediaItem.fromUri(videoUrl)
                player?.setMediaItem(mediaItem)
                player?.prepare()
                player?.play()
            }

            else -> {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        EventManager.unregister(this)
        player?.release()
        player = null
    }

}