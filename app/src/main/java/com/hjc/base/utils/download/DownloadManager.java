package com.hjc.base.utils.download;


import com.hjc.base.http.Api;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: HJC
 * @Date: 2019/7/29 14:16
 * @Description: 下载管理类
 */
public class DownloadManager {
    private static DownloadManager downloadManager;

    private DownloadManager() {
    }

    public static DownloadManager getInstance() {
        if (downloadManager == null) {
            synchronized (DownloadManager.class) {
                if (downloadManager == null) {
                    downloadManager = new DownloadManager();
                }
            }
        }
        return downloadManager;
    }

    public void download(String url, final BaseDownloadObserver<File> downloadObserver) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("http://www.apk.anzhi.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofit.create(Api.class)
                .downloadApk(url)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return downloadObserver.writeToFile(responseBody);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(downloadObserver);
    }

}
