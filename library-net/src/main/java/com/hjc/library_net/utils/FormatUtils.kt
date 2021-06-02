package com.hjc.library_net.utils

import com.blankj.utilcode.util.LogUtils

/**
 * @Author: HJC
 * @Date: 2019/3/7 16:33
 * @Description: 格式化Log工具类
 */

object FormatUtils {

    /**
     * 将json格式化并在logcat中输出
     * @param jsonStr json字符串
     */
    fun formatJsonAndLog(jsonStr: String) {
        var level = 0
        val sb = StringBuffer()
        for (element in jsonStr) {
            if (level > 0 && '\n' == sb[sb.length - 1]) {
                sb.append(getLevelStr(level))
            }
            when (element) {
                '{', '[' -> {
                    sb.append(element + "\n")
                    level++
                }
                ',' -> sb.append(element + "\n")
                '}', ']' -> {
                    sb.append("\n")
                    level--
                    sb.append(getLevelStr(level))
                    sb.append(element)
                }
                else -> sb.append(element)
            }
        }

        val formatJson = sb.toString()

        var index = 0
        var max = 3800
        var sub: String
        while (index < formatJson.length) {
            if (formatJson.length < max) {
                max = formatJson.length
                sub = formatJson.substring(index, max)
            } else {
                sub = formatJson.substring(index, max)
            }
            LogUtils.e(sub)
            index = max
            max += 3800
        }
    }

    private fun getLevelStr(level: Int): String {
        val levelStr = StringBuffer()
        for (levelI in 0 until level) {
            levelStr.append("\t")
        }
        return levelStr.toString()
    }

}
