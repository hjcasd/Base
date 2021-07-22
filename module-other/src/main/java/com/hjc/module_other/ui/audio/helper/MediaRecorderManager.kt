package com.hjc.module_other.ui.audio.helper

import android.media.MediaRecorder
import android.os.Handler
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.math.log10

/**
 * @Author: HJC
 * @Date: 2021/5/27 4:00 下午
 * @Description: MediaRecorder管理类
 */
class MediaRecorderManager private constructor(private val mDir: String?) {

    private var mMediaRecorder: MediaRecorder? = null

    // 当前录音文件路径
    private var mCurrentFilePath: String? = null

    // 是否准备好录音
    private var isPrepared = false

    // 参考振幅为 1
    private val baseAmplitude = 1

    // 间隔取样时间
    private val spaceTime = 100L

    private var startTime: Long = 0

    private var endTime: Long = 0

    // 录音回调
    private var mListener: AudioStateListener? = null

    companion object {

        @Volatile
        private var mInstance: MediaRecorderManager? = null

        fun getInstance(dir: String?) = mInstance ?: synchronized(this) {
            mInstance ?: MediaRecorderManager(dir).also { mInstance = it }
        }
    }

    private val mHandler: Handler = Handler()

    private val mUpdateMicStatusTimer = Runnable { updateMicStatus() }

    /**
     * 准备录音
     */
    fun startRecord() {
        try {
            isPrepared = false

            val dir = File(mDir)
            if (!dir.exists()) {
                dir.mkdir()
            }
            val fileName = generateFileName()
            val file = File(dir, fileName)
            mCurrentFilePath = file.absolutePath

            mMediaRecorder = MediaRecorder()
            // 设置输出文件
            mMediaRecorder?.setOutputFile(file.absolutePath)
            // 设置音频源为麦克风
            mMediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
            // 设置音频输出格式
            mMediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
            // 设置采样率
            mMediaRecorder?.setAudioSamplingRate(8000)
            // 设置音频的编码为amr格式
            mMediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mMediaRecorder?.prepare()
            mMediaRecorder?.start()

            isPrepared = true
            startTime = System.currentTimeMillis()
            mListener?.onStart()
            updateMicStatus()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 随机生成录音文件名(生成UUID唯一标示符)
     */
    private fun generateFileName(): String {
        return UUID.randomUUID().toString() + ".amr"
    }

    /**
     * 更新麦克状态
     */
    private fun updateMicStatus() {
        if (isPrepared) {
            // 分贝
            var db = 0.0
            mMediaRecorder?.let {
                val ratio = it.maxAmplitude.toDouble() / baseAmplitude
                if (ratio > 1) {
                    db = 20 * log10(ratio)
                }
            }
            mListener?.onUpdate(db, System.currentTimeMillis() - startTime)
            mHandler.postDelayed(mUpdateMicStatusTimer, spaceTime)
        }
    }


    /**
     * 停止录音
     */
    fun stopRecord(): Long {
        endTime = System.currentTimeMillis()
        try {
            release()
            mListener?.onStop(mCurrentFilePath)
        } catch (e: RuntimeException) {
            release()
            deleteAudioFile()
        }
        return endTime - startTime
    }

    /**
     * 取消录音
     */
    fun cancelRecord() {
        try {
            release()
            deleteAudioFile()
        } catch (e: RuntimeException) {
            release()
            deleteAudioFile()
        }
    }

    /**
     * 释放资源
     */
    private fun release() {
        isPrepared = false
        mMediaRecorder?.stop()
        mMediaRecorder?.reset()
        mMediaRecorder?.release()
        mMediaRecorder = null
        mHandler.removeCallbacksAndMessages(null)
    }


    /**
     * 删除当前录音文件
     */
    private fun deleteAudioFile() {
        mCurrentFilePath?.let {
            val file = File(mCurrentFilePath)
            if (file.exists()) {
                file.delete()
            }
            mCurrentFilePath = null
        }
    }

    /**
     * 回调准备完毕
     */
    interface AudioStateListener {
        /**
         * 开始录音
         */
        fun onStart()

        /**
         * 录音中...
         * @param db 当前声音分贝
         * @param time 录音时长
         */
        fun onUpdate(db: Double, time: Long)

        /**
         * 停止录音
         * @param filePath 保存路径
         */
        fun onStop(filePath: String?)
    }

    fun setOnAudioStateListener(listener: AudioStateListener) {
        this.mListener = listener
    }
}