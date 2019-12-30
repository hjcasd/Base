package com.hjc.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.content.FileProvider;

import com.hjc.base.BuildConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:36
 * @Description: 相机相册工具类
 */
public class PhotoUtils {
    public static final int REQUEST_OPEN_CAMERA = 1;  // 拍照
    public static final int REQUEST_OPEN_ALBUM = 2;   // 打开系统相册

    /**
     * 打开系统相机
     * @param activity 当前页面
     * @param picturePath 图片存储路径
     */
    public static void openCamera(Activity activity, String picturePath) {
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file = new File(picturePath);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity.getApplicationContext(),
                        BuildConfig.APPLICATION_ID + ".fileProvider", file));
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            }
            activity.startActivityForResult(intent, REQUEST_OPEN_CAMERA);
        }
    }

    /**
     * 打开系统相机
     * @param activity 当前页面
     */
    public static void openCamera(Activity activity) {
        // 判断存储卡是否可以用，可用进行存储
        if (hasSdcard()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss", Locale.getDefault());
            String time = sdf.format(new Date());
            String pictureName = time + ".jpg";
            //图片路径
            String picturePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + pictureName;
            File file = new File(picturePath);
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(activity.getApplicationContext(),
                        BuildConfig.APPLICATION_ID + ".fileProvider", file));
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            }
            activity.startActivityForResult(intent, REQUEST_OPEN_CAMERA);
        }
    }

    /**
     * 打开系统相册
     * @param activity 当前页面
     */
    public static void openAlbum(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, REQUEST_OPEN_ALBUM);
    }

    /**
     * 判断sdcard是否被挂载
     */
    private static boolean hasSdcard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
