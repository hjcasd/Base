package com.hjc.module_other.utils.update

import com.hjc.module_other.http.OtherApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:16
 * @Description: 下载管理类
 */
object DownloadManager {

    fun download(url: String?, downloadObserver: BaseDownloadObserver<File>) {
        val retrofit: Retrofit = Retrofit.Builder()
            .client(OkHttpClient())
            .baseUrl("http://www.apk.anzhi.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(OtherApiService::class.java)
            .downloadApk(url)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { responseBody -> downloadObserver.writeToFile(responseBody) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(downloadObserver)
    }

}