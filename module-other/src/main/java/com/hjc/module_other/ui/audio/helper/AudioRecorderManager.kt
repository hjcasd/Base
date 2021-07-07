package com.hjc.module_other.ui.audio.helper

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import com.blankj.utilcode.util.LogUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.log10

/**
 * @Author: HJC
 * @Date: 2021/7/7 16:29
 * @Description: AudioRecord录音管理类
 */
class AudioRecorderManager private constructor(private val mDir: String) {

    private var mAudioRecord: AudioRecord? = null

    // 缓冲区字节大小
    private var bufferSizeInBytes = 0

    // raw文件路径
    private var audioRawPath = ""

    // wav文件路径
    private var audioWavPath = ""

    // 是否正在录音
    private var isRecord = false

    private var startTime: Long = 0

    private var endTime: Long = 0

    // 录音回调
    private var mListener: AudioStateListener? = null

    companion object {

        // 音频输入-麦克风
        const val AUDIO_INPUT = MediaRecorder.AudioSource.MIC

        // 采样频率, 44100是目前的标准，但是某些设备仍然支持22050，16000，11025
        const val AUDIO_SAMPLE_RATE = 8000

        @Volatile
        private var mInstance: AudioRecorderManager? = null

        fun getInstance(dir: String) = mInstance ?: synchronized(this) {
            mInstance ?: AudioRecorderManager(dir).also { mInstance = it }
        }
    }

    /**
     * 开始录音
     */
    fun startRecord() {
        if (!isRecord) {
            if (mAudioRecord == null) {
                createAudioRecord()
            }
            mAudioRecord?.startRecording()

            isRecord = true
            startTime = System.currentTimeMillis()
            mListener?.onStart()

            Thread(GetVoiceRunnable()).start()
            Thread(AudioRecordRunnable()).start()
        }
    }

    private fun createAudioRecord() {
        // 获取音频文件路径
        audioRawPath = AudioFileUtils.getRawFilePath(mDir)
        audioWavPath = AudioFileUtils.getWavFilePath(mDir)

        // 获得缓冲区字节大小
        bufferSizeInBytes = AudioRecord.getMinBufferSize(AUDIO_SAMPLE_RATE, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT)

        // 创建AudioRecord对象
        mAudioRecord =
            AudioRecord(AUDIO_INPUT, AUDIO_SAMPLE_RATE, AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT, bufferSizeInBytes)
    }

    /**
     * 停止录音
     */
    fun stopRecord() {
        endTime = System.currentTimeMillis()
        try {
            release()
            mListener?.onStop(audioWavPath)
        } catch (e: RuntimeException) {
            release()
            deleteAudioFile()
        }
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
     * 删除当前录音文件
     */
    private fun deleteAudioFile() {
        val rawFile = File(audioRawPath)
        if (rawFile.exists()) {
            rawFile.delete()
        }
        val wavFile = File(audioWavPath)
        if (wavFile.exists()) {
            wavFile.delete()
        }
        audioRawPath = ""
        audioWavPath = ""
    }

    /**
     * 释放资源
     */
    private fun release() {
        isRecord = false
        mAudioRecord?.stop()
        mAudioRecord?.release()
        mAudioRecord = null
    }

    /**
     * 音频文件写入线程
     */
    private inner class AudioRecordRunnable : Runnable {
        override fun run() {
            writeDataToRawFile()
            copyRawDataToWaveFile(audioRawPath, audioWavPath)
        }
    }

    /**
     * 获取实时音量线程
     */
    private inner class GetVoiceRunnable : Runnable {
        override fun run() {
            val buffer = ShortArray(bufferSizeInBytes)
            while (isRecord) {
                mAudioRecord?.let {
                    //r是实际读取的数据长度，一般而言r会小于bufferSize
                    val r = it.read(buffer, 0, bufferSizeInBytes)
                    var v: Long = 0
                    // 将 buffer 内容取出，进行平方和运算
                    for (value in buffer) {
                        v += (value * value).toLong()
                    }
                    // 平方和除以数据总长度，得到音量大小。
                    val mean = v / r.toDouble()
                    val volume = 10 * log10(mean)

                    LogUtils.e("分贝值 = $volume dB")

                    Thread.sleep(100)
                    mListener?.onUpdate(volume, System.currentTimeMillis() - startTime)
                }
            }
        }
    }

    /**
     * 往文件中写入裸数据
     * 但是并不能播放，因为AudioRecord获得的音频是原始的裸音频，
     * 如果需要播放就必须加入一些格式或者编码的头信息。但是这样的好处就是你可以对音频的 裸数据进行处理
     * 比如你要做一个爱说话的TOM猫在这里就进行音频的处理，然后重新封装 所以说这样得到的音频比较容易做一些音频的处理。
     */
    private fun writeDataToRawFile() {
        // new一个byte数组用来存一些字节数据，大小为缓冲区大小
        val audioData = ByteArray(bufferSizeInBytes)
        var fos: FileOutputStream? = null
        var readSize: Int
        try {
            val file = File(audioRawPath)
            if (file.exists()) {
                file.delete()
            }
            fos = FileOutputStream(file) // 建立一个可存取字节的文件
        } catch (e: Exception) {
            e.printStackTrace()
        }
        while (isRecord) {
            mAudioRecord?.let {
                readSize = it.read(audioData, 0, bufferSizeInBytes)
                if (AudioRecord.ERROR_INVALID_OPERATION != readSize) {
                    try {
                        fos?.write(audioData)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        try {
            fos?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 给裸数据加上头文件,将.raw文件转换为.wav可播放录音文件
     *
     * @param rawFilename raw文件名
     * @param wavFilename wav文件名
     */
    private fun copyRawDataToWaveFile(rawFilename: String?, wavFilename: String?) {
        val fis: FileInputStream
        val fos: FileOutputStream
        val totalAudioLen: Long
        val totalDataLen: Long
        val channels = 2
        val byteRate = (16 * AUDIO_SAMPLE_RATE * channels / 8).toLong()
        val data = ByteArray(bufferSizeInBytes)
        try {
            fis = FileInputStream(rawFilename)
            fos = FileOutputStream(wavFilename)
            totalAudioLen = fis.channel.size()
            totalDataLen = totalAudioLen + 36
            WavHelper.writeWaveFileHeader(fos, totalAudioLen, totalDataLen, AUDIO_SAMPLE_RATE.toLong(), channels, byteRate)
            while (fis.read(data) != -1) {
                fos.write(data)
            }
            fis.close()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
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