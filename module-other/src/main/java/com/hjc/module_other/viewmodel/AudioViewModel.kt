package com.hjc.module_other.viewmodel

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.hjc.library_common.viewmodel.KotlinViewModel
import com.hjc.module_other.model.AudioModel
import java.io.File

class AudioViewModel(application: Application) : KotlinViewModel(application) {

    private val audioModel = AudioModel()

    fun uploadVoiceFile(filePath: String) {
        val file = File(filePath)
        launchOriginal({
            audioModel.uploadVoiceFile(file)
        }, {result ->
            LogUtils.e("result: $result")
        }, isShowLoading = true)
    }
}