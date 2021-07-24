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
import com.hjc.module_other.view.RecordVoiceButton
import com.hjc.module_other.viewmodel.AudioViewModel
import java.io.IOException

/**
 * @Author: HJC
 * @Date: 2021/7/7 22:44
 * @Description: 录音
 */
@Route(path = RouteOtherPath.URL_AUDIO)
class AudioActivity : BaseActivity<OtherActivityAudioBinding, AudioViewModel>() {

    private var mPlayer: MediaPlayer? = null

    private lateinit var mFilePath: String

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
        mPlayer = MediaPlayer()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun leftClick(view: View?) {
                finish()
            }
        })

        mBindingView.rvbVoice.setOnAudioRecorderListener(object : RecordVoiceButton.AudioRecorderListener {

            override fun onFinish(recordTime: Long, filePath: String) {
                LogUtils.e("filePath: $filePath")
                mFilePath = filePath
//                mViewModel?.uploadVoiceFile(mFilePath)
            }

            override fun onStateChange(state: Int) {

            }

        })
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.btn1 -> {
                if (!TextUtils.isEmpty(mFilePath)) {
                    try {
                        mPlayer = MediaPlayer()
                        mPlayer?.setDataSource(mFilePath)
                        mPlayer?.prepare()
                        mPlayer?.start()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    ToastUtils.showShort("请先录音")
                }
            }

            R.id.btn2 -> {
                mPlayer?.stop()
                mPlayer?.reset()
                mPlayer?.release()
                mPlayer = null
            }

            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer?.stop()
        mPlayer?.release()
        mPlayer = null
    }
}