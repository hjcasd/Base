package com.hjc.module_other.utils.update

import com.hjc.module_other.utils.ApkUtils
import io.reactivex.observers.DefaultObserver
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:16
 * @Description: 下载Observer
 */
abstract class BaseDownloadObserver<T> : DefaultObserver<T>() {

    override fun onNext(t: T) {
        onDownloadSuccess(t)
    }

    override fun onError(e: Throwable) {
        onDownloadFail(e.toString())
    }

    override fun onComplete() {}

    /**
     * 下载成功的回调
     *
     * @param t 下载数据
     */
    abstract fun onDownloadSuccess(t: T)

    /**
     * 下载失败回调
     *
     * @param msg 失败信息
     */
    abstract fun onDownloadFail(msg: String?)

    /**
     * 下载进度监听
     *
     * @param progress 实时进度
     * @param total    总进度
     */
    abstract fun onProgress(progress: Int, total: Long)

    /**
     * 将文件写入本地
     *
     * @param responseBody 请求结果全体
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    @Throws(IOException::class)
    fun writeToFile(responseBody: ResponseBody): File {
        var inputStream: InputStream? = null
        var fos: FileOutputStream? = null
        val totalLength: Long = responseBody.contentLength()
        var currentLength: Long = 0
        val buf = ByteArray(2048)
        var len: Int

        return try {
            val dir = File(ApkUtils.getApkDir())
            if (!dir.exists()) {
                dir.mkdirs()
            }

            val file = File(dir, ApkUtils.getApkPath())
            inputStream = responseBody.byteStream()
            fos = FileOutputStream(file)
            while (inputStream.read(buf).also { len = it } != -1) {
                currentLength += len.toLong()
                fos.write(buf, 0, len)
                onProgress((currentLength * 100 / totalLength).toInt(), totalLength)
            }
            fos.flush()
            file
        } finally {
            inputStream?.close()
            fos?.close()
        }
    }
}