package com.hjc.module_other.ui.audio.helper

import java.io.File
import java.util.*

object AudioFileUtils {

    /**
     * 获取麦克风输入的原始音频流raw格式文件路径
     *
     * @param dir 文件目录
     * @return raw文件路径
     */
    fun getRawFilePath(dir: String): String {
        val fileBasePath = dir + File.separator + "raw"
        val dirFile = File(fileBasePath)
        //创建目录
        if (!dirFile.exists()) {
            dirFile.mkdirs()
        }
        return fileBasePath + "/" + generalRawFileName()
    }

    private fun generalRawFileName(): String {
        return UUID.randomUUID().toString() + ".raw"
    }

    /**
     * 获取编码后的WAV格式音频文件路径
     *
     * @param dir 文件目录
     * @return wav文件路径
     */
    fun getWavFilePath(dir: String): String {
        val fileBasePath = dir + File.separator + "wav"
        val dirFile = File(fileBasePath)
        //创建目录
        if (!dirFile.exists()) {
            dirFile.mkdirs()
        }
        return fileBasePath + "/" + generalWavFileName()
    }

    private fun generalWavFileName(): String {
        return UUID.randomUUID().toString() + ".wav"
    }
}