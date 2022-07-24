package com.hjc.module_other.ui.camera

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.gyf.immersionbar.ImmersionBar
import com.hjc.library_base.activity.BaseActivity
import com.hjc.library_common.router.path.RouteOtherPath
import com.hjc.library_common.viewmodel.CommonViewModel
import com.hjc.module_other.R
import com.hjc.module_other.databinding.OtherActivityCameraXBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author: HJC
 * @Date: 2021/7/7 22:44
 * @Description: CameraX的使用
 */
@Route(path = RouteOtherPath.URL_CAMERA_X)
class CameraXActivity : BaseActivity<OtherActivityCameraXBinding, CommonViewModel>() {

    // 预览对象
    private var preview: Preview? = null

    // 相机对象
    private var camera: Camera? = null

    // 图片拍摄
    private var imageCapture: ImageCapture? = null

    // 当前相机为后置摄像机
    private var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    // 相机信息
    private var cameraProvider: ProcessCameraProvider? = null

    // 保存照片路径
    private var filePath = ""

    override fun getLayoutId(): Int {
        return R.layout.other_activity_camera_x
    }

    override fun createViewModel(): CommonViewModel? {
        return null
    }

    override fun getImmersionBar(): ImmersionBar? {
        return ImmersionBar.with(this)
    }

    override fun initData(savedInstanceState: Bundle?) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US)
        filePath = cacheDir.absolutePath + File.separator + simpleDateFormat.format(Date()).toString() + ".jpg"

        startCamera()
    }

    override fun addListeners() {
        mBindingView.onClickListener = this
    }

    override fun onSingleClick(v: View?) {
        when (v?.id) {
            R.id.iv_cancel -> {
                startCamera()
            }

            R.id.iv_camera -> {
                takePhoto()
            }

            R.id.iv_confirm -> {
                finish()
            }

            else -> {
            }
        }
    }

    /**
     * 启动相机
     */
    private fun startCamera() {
        FileUtils.delete(filePath)
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            // 相机信息
            cameraProvider = cameraProviderFuture.get()
            // 预览配置
            preview = Preview.Builder().build()
            // 创建图片Capture
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()
            try {
                // 先解绑所有用例
                cameraProvider?.unbindAll()
                // 绑定用例
                camera = cameraProvider?.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture,
                )
                preview?.setSurfaceProvider(mBindingView.viewFinder.createSurfaceProvider(camera?.cameraInfo))
            } catch (e: Exception) {
                LogUtils.e("Use case binding failed: $e")
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {
        val file = File(filePath)
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

        imageCapture?.takePicture(outputOptions, ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(e: ImageCaptureException) {
                    LogUtils.e("Photo capture failed: ${e.message}")
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    val savedUri = Uri.fromFile(file)
                    LogUtils.e("Photo capture succeeded: $savedUri")
                    cameraProvider?.unbindAll()
                }
            })
    }

}