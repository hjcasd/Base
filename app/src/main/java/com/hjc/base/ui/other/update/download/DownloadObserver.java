package com.hjc.base.ui.other.update.download;


import com.hjc.base.constant.AppConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.observers.DefaultObserver;
import okhttp3.ResponseBody;

public abstract class DownloadObserver<T> extends DefaultObserver<T> {

    @Override
    public void onNext(T t) {
        onDownloadSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onDownloadFail(e.toString());
    }

    @Override
    public void onComplete() {
    }

    //下载成功的回调
    public abstract void onDownloadSuccess(T t);

    //下载失败回调
    public abstract void onDownloadFail(String msg);

    //下载进度监听
    public abstract void onProgress(int progress, long total);

    /**
     * 将文件写入本地
     *
     * @param responseBody 请求结果全体
     * @return 写入完成的文件
     * @throws IOException IO异常
     */
    public File writeToFile(ResponseBody responseBody) throws IOException {
        InputStream inputStream = null;
        FileOutputStream fos = null;
        long totalLength = responseBody.contentLength();
        long currentLength = 0;
        byte[] buf = new byte[2048];
        int len;
        try {
            File dir = new File(AppConstants.DOWNLOAD_APK_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, AppConstants.DOWNLOAD_APK_NAME);

            inputStream = responseBody.byteStream();
            fos = new FileOutputStream(file);
            while ((len = inputStream.read(buf)) != -1) {
                currentLength += len;
                fos.write(buf, 0, len);
                onProgress((int) (currentLength * 100 / totalLength), totalLength);
            }
            fos.flush();
            return file;

        } finally {
            try {
                if (inputStream != null) inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
