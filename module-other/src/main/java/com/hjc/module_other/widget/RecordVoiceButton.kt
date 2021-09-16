package com.hjc.module_other.widget

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnLongClickListener
import androidx.appcompat.widget.AppCompatImageButton
import com.hjc.module_other.R
import com.hjc.module_other.dialog.RecordDialog
import com.hjc.module_other.utils.audio.MediaRecorderManager

/**
 * @Author: HJC
 * @Date: 2021/5/28 14:50
 * @Description: 自定义录音按钮类(MediaRecorder)
 */
class RecordVoiceButton @JvmOverloads constructor(
    mContext: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatImageButton(mContext, attrs, defStyleAttr), MediaRecorderManager.AudioStateListener, OnLongClickListener {

    private var mDialogManager: RecordDialog? = null
    private var mRecorderManager: MediaRecorderManager? = null

    //当前状态
    private var mCurrentState = STATE_NORMAL

    //是否开始录音
    private var isRecording = false

    //是否触发长按事件
    private var mReady = false

    //录音时长(ms)
    private var mRecordTime: Long = 0

    private var mListener: AudioRecorderListener? = null

    @SuppressLint("HandlerLeak")
    private val mHandler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                MSG_AUDIO_PREPARED -> {
                    mDialogManager?.show()
                    isRecording = true
                }
                MSG_DIALOG_DISMISS -> mDialogManager?.dismiss()
            }
        }
    }

    companion object {
        //手指滑动 距离
        private const val DISTANCE_Y_CANCEL = 50

        //状态
        private const val STATE_NORMAL = 1
        private const val STATE_RECORDING = 2
        private const val STATE_WANT_TO_CANCEL = 3
        private const val MSG_AUDIO_PREPARED = 0X110
        private const val MSG_DIALOG_DISMISS = 0X112
    }

    init {
        mDialogManager = RecordDialog(mContext)
        val dir = mContext.cacheDir.absolutePath + "/recorder"
        mRecorderManager = MediaRecorderManager.getInstance(dir)
        mRecorderManager?.setOnAudioStateListener(this)
        setOnLongClickListener(this)
    }

    override fun onLongClick(v: View): Boolean {
        // 长按按钮开始录音
        mReady = true
        mRecorderManager?.startRecord()
        return false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action
        val x = event.x.toInt()
        val y = event.y.toInt()
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                isRecording = true
                changeState(STATE_RECORDING)
            }
            MotionEvent.ACTION_MOVE -> if (isRecording) {
                if (wantToCancel(x, y)) {
                    changeState(STATE_WANT_TO_CANCEL)
                } else {
                    changeState(STATE_RECORDING)
                }
            }
            MotionEvent.ACTION_UP -> {
                //如果长按事件没触发
                if (!mReady) {
                    reset()
                    return super.onTouchEvent(event)
                }
                if (!isRecording || mRecordTime < 500) {
                    //触发了长按事件,但是录音时间太短
                    mDialogManager?.showTooShort()
                    mRecorderManager?.cancelRecord()
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DISMISS, 1000)
                } else if (mCurrentState == STATE_RECORDING) {
                    //正常录音结束
                    mDialogManager?.dismiss()
                    mRecorderManager?.stopRecord()
                } else if (mCurrentState == STATE_WANT_TO_CANCEL) {
                    //滑动取消录音
                    mDialogManager?.dismiss()
                    mRecorderManager?.cancelRecord()
                }
                reset()
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 恢复状态 标志位
     */
    private fun reset() {
        isRecording = false
        mReady = false
        changeState(STATE_NORMAL)
        mRecordTime = 0L
    }

    /**
     * 根据想x,y的坐标，判断是否想要取消
     *
     * @param x 坐标
     * @param y 坐标
     * @return 结果
     */
    private fun wantToCancel(x: Int, y: Int): Boolean {
        //如果左右滑出 button
        return if (x < 0 || x > width) {
            true
        } else y < -DISTANCE_Y_CANCEL || y > height + DISTANCE_Y_CANCEL
        //如果上下滑出 button  加上我们自定义的距离
    }

    /**
     * 改变状态
     *
     * @param state 状态
     */
    private fun changeState(state: Int) {
        if (mCurrentState != state) {
            mCurrentState = state
            when (state) {
                STATE_NORMAL -> {
                    setBackgroundResource(R.mipmap.other_img_stop)
                    mListener?.onStateChange(STATE_NORMAL)
                }
                STATE_RECORDING -> {
                    setBackgroundResource(R.mipmap.other_img_play)
                    if (isRecording) {
                        mDialogManager?.showRecording()
                        mListener?.onStateChange(STATE_RECORDING)
                    }
                }
                STATE_WANT_TO_CANCEL -> {
                    setBackgroundResource(R.mipmap.other_img_play)
                    mListener?.onStateChange(STATE_WANT_TO_CANCEL)
                    mDialogManager?.showWantToCancel()
                }
            }
        }
    }

    override fun onStart() {
        mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED)
    }

    override fun onUpdate(db: Double, time: Long) {
        mRecordTime = time
        mDialogManager?.updateVoiceImage(db)
    }

    override fun onStop(filePath: String) {
        mListener?.onFinish(mRecordTime, filePath)
    }

    /**
     * 录音完成后的回调
     */
    interface AudioRecorderListener {
        /**
         * 录音完成回调
         * @param recordTime 录音时长
         * @param filePath 文件路径
         */
        fun onFinish(recordTime: Long, filePath: String)

        /**
         * 录音状态回调
         * @param state 状态
         */
        fun onStateChange(state: Int)
    }

    fun setOnAudioRecorderListener(listener: AudioRecorderListener?) {
        mListener = listener
    }

}