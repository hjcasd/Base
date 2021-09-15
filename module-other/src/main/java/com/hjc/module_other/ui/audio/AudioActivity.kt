package com.hjc.module_other.ui.audio

import android.media.MediaPlayer
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
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
import com.hjc.module_other.dialog.AudioDialog
import com.hjc.module_other.viewmodel.AudioViewModel
import com.hjc.module_other.widget.RecordVoiceButton
import java.io.File
import java.io.FileOutputStream


/**
 * @Author: HJC
 * @Date: 2021/7/7 22:44
 * @Description: 录音
 */
@Route(path = RouteOtherPath.URL_AUDIO)
class AudioActivity : BaseActivity<OtherActivityAudioBinding, AudioViewModel>() {

    private var mPlayer: MediaPlayer? = null

    private var mFilePath: String = ""

    private var audioStr =
        "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"

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
        mFilePath = cacheDir.absolutePath + "/tmp.wav"
        writeToFile(mFilePath)
    }

    /**
     * 将base64编码的字符串写入到文件中
     */
    private fun writeToFile(filePath: String) {
        val file = File(filePath)
        if (file.exists()) {
            file.delete()
        }
        val bytes = Base64.decode(audioStr, 0)
        val os = FileOutputStream(file, true)
        os.write(bytes)
        os.flush()
        os.close()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.titleBar.setOnViewLeftClickListener(object : OnViewLeftClickListener {
            override fun onViewLeftClick(view: View?) {
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
                        mPlayer?.let {
                            if (it.isPlaying) {
                                it.stop()
                                it.reset()
                                it.release()
                            }
                        }
                        mPlayer = null
                        mPlayer = MediaPlayer()
                        mPlayer?.setDataSource(mFilePath)
                        mPlayer?.prepare()
                        mPlayer?.start()
                    } catch (e: IllegalStateException) {
                        mPlayer = null
                    }
                } else {
                    ToastUtils.showShort("请先录音")
                }
            }

            R.id.btn2 -> {
                mPlayer?.let {
                    if (it.isPlaying) {
                        it.stop()
                        it.reset()
                        it.release()
                    }
                    mPlayer = null
                }
            }

            R.id.btn3 -> {
                AudioDialog.newInstance().showDialog(supportFragmentManager)
            }

            else -> {
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.reset()
                it.release()
            }
            mPlayer = null
        }
    }
}