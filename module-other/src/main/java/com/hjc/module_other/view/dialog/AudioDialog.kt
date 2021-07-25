package com.hjc.module_other.view.dialog

import android.annotation.SuppressLint
import android.media.AudioFormat
import android.media.MediaRecorder
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ToastUtils
import com.hjc.library_base.dialog.BaseFragmentDialog
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.library_net.utils.RxSchedulers
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherDialogAudioBinding
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import tech.oom.idealrecorder.IdealRecorder
import tech.oom.idealrecorder.IdealRecorder.RecordConfig
import tech.oom.idealrecorder.StatusListener
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:30
 * @Description: 录音dialog
 */
class AudioDialog : BaseFragmentDialog<OtherDialogAudioBinding, CommonViewModel>() {

    private var idealRecorder: IdealRecorder? = null
    private var recordConfig: RecordConfig? = null

    private var disposable: Disposable? = null

    private var filePath: String = ""

    //当前录音时间(ms)
    private var currentTime = 0L

    //录音时长(s)
    private var recordTime = 0

    companion object {
        fun newInstance(): AudioDialog {
            return AudioDialog()
        }
    }

    override fun getStyleRes(): Int {
        return R.style.Base_Dialog_Action_Sheet
    }

    override fun getLayoutId(): Int {
        return R.layout.other_dialog_audio
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun initData(savedInstanceState: Bundle?) {
        idealRecorder = IdealRecorder.getInstance()
        //Recorder的配置信息 采样率 采样位数
        recordConfig = RecordConfig(
            MediaRecorder.AudioSource.MIC,
            RecordConfig.SAMPLE_RATE_22K_HZ, AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun addListeners() {
        mBindingView.onClickListener = this

        mBindingView.tvStartRecording.setOnLongClickListener {
            readyRecord()
            true
        }

        mBindingView.tvStartRecording.setOnTouchListener(OnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_UP -> {
                    stopRecord()
                    return@OnTouchListener false
                }
            }
            false
        })
    }

    /**
     * 准备录音
     */
    private fun readyRecord() {
        deleteFile()
        record()
    }

    /**
     * 开始录音
     */
    private fun record() {
        val file = createFile()
        //如果需要保存录音文件  设置好保存路径就会自动保存  也可以通过onRecordData 回调自己保存  不设置 不会保存录音
        idealRecorder?.setRecordFilePath(file.absolutePath)
        //设置录音配置 最长录音时长 以及音量回调的时间间隔
        idealRecorder?.setRecordConfig(recordConfig)
            ?.setMaxRecordTime(Int.MAX_VALUE.toLong())
            ?.setVolumeInterval(200)
        //设置录音时各种状态的监听
        idealRecorder?.setStatusListener(statusListener)
        //开始录音
        idealRecorder?.start()
    }

    private fun createFile(): File {
        val fileName = "record" + System.currentTimeMillis() + ".wav"
        filePath = mContext.cacheDir.absolutePath + "/record/" + fileName

        val file = File(filePath)
        if (!file.exists()) {
            file.mkdir()
        }
        return file
    }

    private fun deleteFile() {
        val path = mContext.cacheDir.absolutePath + "/record"
        val dirFile = File(path)
        if (dirFile.exists()) {
            dirFile.listFiles { file -> file.delete() }
        }
    }


    /**
     * 停止录音
     */
    private fun stopRecord() {
        idealRecorder?.stop()
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.tv_start_recording -> {

            }

            else -> {
            }
        }
    }

    /**
     * 录音状态监听回调
     */
    private val statusListener: StatusListener = object : StatusListener() {

        override fun onStartRecording() {
            countDownTimer()
            mBindingView.tvRecordingTip.visibility = View.GONE
            mBindingView.tvRecordTime.visibility = View.VISIBLE
            mBindingView.waveView.visibility = View.VISIBLE
        }

        override fun onRecordData(data: ShortArray, length: Int) {
            var i = 0
            while (i < length) {
                mBindingView.waveView.addData(data[i])
                i += 60
            }
        }

        override fun onVoiceVolume(volume: Int) {
            LogUtils.e("volume: $volume")
        }

        override fun onRecordError(code: Int, errorMsg: String) {
            LogUtils.e("onRecordError: $errorMsg")
        }

        override fun onFileSaveFailed(error: String) {
            ToastUtils.showShort("文件保存失败")
        }

        override fun onFileSaveSuccess(fileUri: String) {
            LogUtils.e("onFileSaveSuccess: $fileUri")
        }

        override fun onStopRecording() {
            recordTime = (currentTime / 1000).toInt()
            currentTime = 0L
            disposable?.dispose()
        }
    }

    /**
     * 启用计时器功能
     */
    private fun countDownTimer() {
        disposable = Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
            .compose(RxSchedulers.ioToMain())
            .subscribe {
                currentTime += 1000
                val time = getFormatHMS(currentTime)
                mBindingView.tvRecordTime.text = time
            }
    }

    /**
     * 时间毫秒数转hh:mm:ss
     */
    private fun getFormatHMS(time: Long): String {
        var t = time
        t /= 1000
        val s = (t % 60).toInt()
        val m = (t / 60).toInt()
        val h = (t / 3600).toInt()
        return String.format("%02d:%02d:%02d", h, m, s)
    }

}