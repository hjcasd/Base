package com.hjc.module_other.ui.video.child

import android.os.Bundle
import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.hjc.library_base.event.EventManager
import com.hjc.library_base.event.MessageEvent
import com.hjc.library_base.fragment.BaseFragment
import com.hjc.library_common.global.EventCode
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherFragmentVideoBinding
import com.hjc.module_other.ui.video.cache.CacheDataSourceFactory
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.math.round


/**
 * @Author: HJC
 * @Date: 2019/7/26 10:42
 * @Description: 展示视频
 */
class VideoFragment : BaseFragment<OtherFragmentVideoBinding, CommonViewModel>() {

    private var player: SimpleExoPlayer? = null

    private var disposable: Disposable? = null

    /**
     * 飞机还是舱位类型
     */
    private var type = 0

    /**
     * 是否第一次播放视频
     */
    private var isFirstPlay = true

    /**
     * 界面是否不可见
     */
    private var isHide = false

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
        player = createPlayer()
        mBindingView.playerView.player = player
        mBindingView.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL

        arguments?.let {
            val videoUrl = it.getString("videoUrl", "")
            type = it.getInt("type", 0)

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
                            isFirstPlay = false
                            player?.pause()
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

    /**
     * 创建播放器
     */
    private fun createPlayer(): SimpleExoPlayer {
        val cacheDataSourceFactory = CacheDataSourceFactory(mContext, 100 * 1024 * 1024, 10 * 1024 * 1024)
        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(cacheDataSourceFactory)
        return SimpleExoPlayer.Builder(mContext)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()
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

                        if (!isFirstPlay && !isHide) {
                            if (playWhenReady) {
                                EventManager.sendEvent(MessageEvent(EventCode.HIDE_RIGHT_PANEL, type))
                            } else {
                                EventManager.sendEvent(MessageEvent(EventCode.SHOW_RIGHT_PANEL, type))
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
        isHide = false
        player?.play()
    }

    override fun onPause() {
        super.onPause()
        isHide = true
        player?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
        player?.release()
        player = null
    }

}