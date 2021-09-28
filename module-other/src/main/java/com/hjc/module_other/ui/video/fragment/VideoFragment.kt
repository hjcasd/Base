package com.hjc.module_other.ui.video.fragment

import android.os.Bundle
import android.view.View
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherFragmentVideoBinding

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
        //设置播放器
        player = SimpleExoPlayer.Builder(mContext).build()
        mBindingView.playerView.player = player

        //播放视频
        val videoUrl = "https://v-cdn.zjol.com.cn/276982.mp4"
        val mediaItem = MediaItem.fromUri(videoUrl)
        player?.setMediaItem(mediaItem)
        player?.prepare()
    }

    override fun addListeners() {

    }

    override fun onSingleClick(v: View?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
    }

}