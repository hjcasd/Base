package com.hjc.module_other.ui.video.child

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherFragmentVideoBinding
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.math.ceil
import kotlin.math.round

/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: 展示视频
 */
class VideoFragment : BaseFragment<OtherFragmentVideoBinding, CommonViewModel>() {

    private var player: SimpleExoPlayer? = null

    private var disposable: Disposable? = null

    private var mType = 0

    private var isFirst = true

    companion object {
        fun newInstance(videoUrl: String, type: Int): VideoFragment {
            val fragment = VideoFragment()
            val bundle = Bundle()
            bundle.putString("videoUrl", videoUrl)
            bundle.putInt("type", type)
            fragment.arguments = bundle
            return fragment
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
        mBindingView.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        arguments?.let {
            val videoUrl = it.getString("videoUrl", "")
            mType = it.getInt("type", 0)

            //播放视频
            val mediaItem = MediaItem.fromUri(videoUrl)
            player?.setMediaItem(mediaItem)
            player?.prepare()
            player?.play()
        }

        Observable.interval(0, 1, TimeUnit.SECONDS)
            .compose(RxSchedulers.ioToMain())
            .subscribe(object : Observer<Long> {
                override fun onSubscribe(d: Disposable) {
                    disposable = d
                }

                override fun onNext(t: Long) {
                    player?.let {
                        val time = round(it.currentPosition / 1000.0).toInt()
                        if (time == 3) {
                            isFirst = false
                            player?.pause()
                            EventManager.sendEvent(MessageEvent(EventCode.SHOW_ALL_VIEW, null))
                            disposable?.dispose()
                        }
                    }
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            })
    }

    override fun addListeners() {
        player?.addListener(object : Player.Listener {

            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> {
                        LogUtils.e("播放器没有可播放的媒体")
                    }

                    Player.STATE_BUFFERING -> {
                        LogUtils.e("视频正在加载中")
                        mBindingView.pbLoading.visibility = View.VISIBLE
                    }

                    Player.STATE_READY -> {
                        LogUtils.e("视频加载完成,可以播放了")
                        mBindingView.pbLoading.visibility = View.GONE
                        if (!isFirst) {
                            if (playWhenReady) {
                                EventManager.sendEvent(MessageEvent(EventCode.HIDE_RIGHT_PANEL, mType))
                            } else {
                                EventManager.sendEvent(MessageEvent(EventCode.SHOW_RIGHT_PANEL, mType))
                            }
                        }
                    }

                    Player.STATE_ENDED -> {
                        LogUtils.e("视频播放结束了")
                    }
                }
            }

        })
    }

    override fun onSingleClick(v: View?) {

    }

    override fun onResume() {
        super.onResume()
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        player = null
    }

}