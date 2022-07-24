package com.hjc.module_other.ui.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.ImageReader;
import android.os.Bundle;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.immersionbar.ImmersionBar;
import com.hjc.library_base.activity.BaseActivity;
import com.hjc.library_common.router.path.RouteOtherPath;
import com.hjc.library_common.viewmodel.CommonViewModel;
import com.hjc.module_other.R;
import com.hjc.module_other.databinding.OtherActivityCamera2Binding;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: HJC
 * @Date: 2022/7/23 20:01
 * @Description: Camera2的使用
 */
@Route(path = RouteOtherPath.URL_CAMERA_2)
public class Camera2Activity extends BaseActivity<OtherActivityCamera2Binding, CommonViewModel> {

    private CameraDevice mCameraDevice;
    private CaptureRequest mCaptureRequest;
    private CameraCaptureSession mPreviewSession;
    private CaptureRequest.Builder mCaptureRequestBuilder;
    private ImageReader mImageReader;
    private Size mPreviewSize;

    private int mCameraWidth;
    private int mCameraHeight;

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    /**
     * 摄像头id（0代表后置摄像头，1代表前置摄像头）
     */
    private final String mCameraId = "0";

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    @Override
    public int getLayoutId() {
        return R.layout.other_activity_camera_2;
    }

    @Nullable
    @Override
    public ImmersionBar getImmersionBar() {
        return ImmersionBar.with(this);
    }

    @Nullable
    @Override
    public CommonViewModel createViewModel() {
        return null;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void addListeners() {
        mBindingView.setOnClickListener(this);

        mBindingView.textureView.setSurfaceTextureListener(surfaceTextureListener);
    }

    @Override
    public void onSingleClick(@Nullable View v) {
        if (v == null) {
            return;
        }
        if (v.getId() == R.id.iv_back) {
            finish();
        } else if (v.getId() == R.id.iv_camera) {
            takePicture();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCameraDevice != null) {
            stopCamera();
        }
    }

    /**
     * 启动相机
     */
    private void startCamera() {
        if (mBindingView.textureView.isAvailable()) {
            if (mCameraDevice == null) {
                openCamera();
            }
        } else {
            mBindingView.textureView.setSurfaceTextureListener(surfaceTextureListener);
        }
    }

    /**
     * 释放相机
     */
    private void stopCamera() {
        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }


    /**
     * TextureView的监听
     */
    private final TextureView.SurfaceTextureListener surfaceTextureListener = new TextureView.SurfaceTextureListener() {

        @Override
        public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
            mCameraWidth = width;
            mCameraHeight = height;
            openCamera();
        }

        @Override
        public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {
        }

        @Override
        public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
            stopCamera();
            return true;
        }

        @Override
        public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {
        }
    };


    /**
     * 打开摄像头
     */
    @SuppressLint("MissingPermission")
    private void openCamera() {
        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        // 设置摄像头特性
        setCameraCharacteristics(manager);
        try {
            manager.openCamera(mCameraId, stateCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置摄像头的参数
     */
    private void setCameraCharacteristics(CameraManager manager) {
        try {
            // 获取指定摄像头的特性
            CameraCharacteristics characteristics = manager.getCameraCharacteristics(mCameraId);
            // 获取摄像头支持的配置属性
            StreamConfigurationMap streamConfigurationMap = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
            if (streamConfigurationMap != null) {
                // 获取摄像头支持的最大尺寸
                Size largest = Collections.max(Arrays.asList(streamConfigurationMap.getOutputSizes(ImageFormat.JPEG)), new CompareSizesByArea());
                // 创建一个ImageReader对象，用于获取摄像头的图像数据
                mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(), ImageFormat.JPEG, 2);
                // 获取最佳的预览尺寸
                mPreviewSize = chooseOptimalSize(streamConfigurationMap.getOutputSizes(SurfaceTexture.class), mCameraWidth, mCameraHeight, largest);
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 为Size定义一个比较器Comparator
     */
    static class CompareSizesByArea implements Comparator<Size> {
        @Override
        public int compare(Size lhs, Size rhs) {
            return Long.signum((long) lhs.getWidth() * lhs.getHeight() - (long) rhs.getWidth() * rhs.getHeight());
        }
    }

    private static Size chooseOptimalSize(Size[] choices, int width, int height, Size aspectRatio) {
        // 收集摄像头支持的大过预览Surface的分辨率
        List<Size> bigEnough = new ArrayList<>();
        int w = aspectRatio.getWidth();
        int h = aspectRatio.getHeight();
        for (Size option : choices) {
            if (option.getHeight() == option.getWidth() * h / w && option.getWidth() >= width && option.getHeight() >= height) {
                bigEnough.add(option);
            }
        }
        if (bigEnough.size() > 0) {
            return Collections.min(bigEnough, new CompareSizesByArea());
        } else {
            //没有合适的预览尺寸
            return choices[0];
        }
    }

    /**
     * 摄像头状态的监听
     */
    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        // 摄像头被打开时触发该方法
        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            mCameraDevice = cameraDevice;
            // 开始预览
            takePreview();
        }

        // 摄像头断开连接时触发该方法
        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            stopCamera();
        }

        // 打开摄像头出现错误时触发该方法
        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int error) {
            stopCamera();
        }
    };

    /**
     * 开始预览
     */
    private void takePreview() {
        SurfaceTexture surfaceTexture = mBindingView.textureView.getSurfaceTexture();
        if (surfaceTexture != null) {
            // 设置TextureView的缓冲区大小
            surfaceTexture.setDefaultBufferSize(mPreviewSize.getWidth(), mPreviewSize.getHeight());
            // 获取Surface显示预览数据
            Surface surface = new Surface(surfaceTexture);
            try {
                // 创建预览请求
                CaptureRequest.Builder captureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                // 设置自动对焦模式
                captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                // 设置Surface作为预览数据的显示界面
                captureRequestBuilder.addTarget(surface);
                // 创建相机捕获会话，第一个参数是捕获数据的输出Surface列表，第二个参数是CameraCaptureSession的状态回调接口，当它创建好后会回调onConfigured方法，第三个参数用来确定Callback在哪个线程执行，为null的话就在当前线程执行
                mCameraDevice.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                    @Override
                    public void onConfigured(@NonNull CameraCaptureSession session) {
                        try {
                            // 开始预览
                            mCaptureRequest = captureRequestBuilder.build();
                            mPreviewSession = session;
                            // 设置反复捕获数据的请求，这样预览界面就会一直有数据显示
                            mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    }
                }, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 拍照
     */
    private void takePicture() {
        try {
            if (mCameraDevice == null) {
                return;
            }
            // 创建拍照请求
            mCaptureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            // 设置自动对焦模式
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            // 将imageReader的surface设为目标
            mCaptureRequestBuilder.addTarget(mImageReader.getSurface());
            // 获取设备方向
            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            // 根据设备方向计算设置照片的方向
            mCaptureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
            // 停止连续取景
            mPreviewSession.stopRepeating();
            // 拍照
            CaptureRequest captureRequest = mCaptureRequestBuilder.build();
            // 设置拍照监听
            mPreviewSession.capture(captureRequest, captureCallback, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听拍照结果
     */
    private final CameraCaptureSession.CaptureCallback captureCallback = new CameraCaptureSession.CaptureCallback() {

        // 拍照成功
        @Override
        public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
            // 重设自动对焦模式
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER, CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
            // 设置自动曝光模式
            mCaptureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE, CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
            try {
                // 重新进行预览
                mPreviewSession.setRepeatingRequest(mCaptureRequest, null, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        // 拍照失败
        @Override
        public void onCaptureFailed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureFailure failure) {
            super.onCaptureFailed(session, request, failure);
        }

    };

}
