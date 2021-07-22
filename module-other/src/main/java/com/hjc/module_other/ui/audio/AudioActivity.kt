package com.hjc.module_other.ui.audio

import android.media.MediaPlayer
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_widget.bar.OnViewLeftClickListener
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityAudioBinding
import com.hjc.module_other.ui.audio.helper.MediaRecorderManager
import com.hjc.module_other.viewmodel.AudioViewModel
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2021/7/7 22:44
 * @Description: 录音
 */
@Route(path = RouteOtherPath.URL_AUDIO)
class AudioActivity : BaseActivity<OtherActivityAudioBinding, AudioViewModel>() {

    //    private var audioRecorderManager: AudioRecorderManager? = null
    private var mediaRecorderManager: MediaRecorderManager? = null

    private var mediaPlayer: MediaPlayer? = null

    private var mFilePath: String? = null

    override fun getLayoutId(): Int {
        return R.layout.other_activity_audio
    }

    override fun createViewModel(): AudioViewModel {
        return ViewModelProvider(this)[AudioViewModel::class.java]
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
            .fitsSystemWindows(true)
            .statusBarColor(R.color.other_color)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val dir = cacheDir.absolutePath
//        audioRecorderManager = AudioRecorderManager.getInstance(dir)
        mediaRecorderManager = MediaRecorderManager.getInstance(dir)

        mediaPlayer = MediaPlayer()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }
        })

//        audioRecorderManager?.setOnAudioStateListener(object : AudioRecorderManager.AudioStateListener {
//
//            override fun onStart() {
//                LogUtils.e("开始录音...")
//            }
//
//            override fun onUpdate(db: Double, time: Long) {
//                LogUtils.e("time: $time")
//            }
//
//            override fun onStop(filePath: String?) {
//                LogUtils.e("filePath: $filePath")
//                mFilePath = filePath
//
//                mFilePath?.let {
//                    mViewModel?.uploadVoiceFile(it)
//                }
//            }
//
//        })

        mediaRecorderManager?.setOnAudioStateListener(object : MediaRecorderManager.AudioStateListener {

            override fun onStart() {
                LogUtils.e("开始录音...")
            }

            override fun onUpdate(db: Double, time: Long) {

            }

            override fun onStop(filePath: String?) {
                LogUtils.e("filePath: $filePath")
                mFilePath = filePath

                mFilePath?.let {
                    mViewModel?.uploadVoiceFile(it)
                }
            }

        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
//                audioRecorderManager?.startRecord()
                mediaRecorderManager?.startRecord()
            }

            R.id.btn2 -> {
//                audioRecorderManager?.stopRecord()
                mediaRecorderManager?.stopRecord()
            }

            R.id.btn3 -> {
//                audioRecorderManager?.cancelRecord()
                mediaRecorderManager?.cancelRecord()
            }

            R.id.btn4 -> {
                if (!TextUtils.isEmpty(mFilePath)) {
                    try {
                        mediaPlayer = MediaPlayer()
                        mediaPlayer?.setDataSource(mFilePath)
                        mediaPlayer?.prepare()
                        mediaPlayer?.start()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    ToastUtils.showShort("请先录音")
                }
            }

            R.id.btn5 -> {
                mediaPlayer?.stop()
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }

            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}